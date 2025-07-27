/*
 * This file is a part of Coverage41C.
 *
 * Copyright (c) 2020-2024
 * Kosolapov Stanislav aka proDOOMman <prodoomman@gmail.com> and contributors
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 *
 * Coverage41C is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * Coverage41C is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Coverage41C.
 */
package com.clouds42.CommandLineOptions;

import com.github._1c_syntax.bsl.support.SupportVariant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine.Option;

import java.lang.invoke.MethodHandles;
import java.util.List;

public class MetadataOptions {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Option(names = {"-s", "--srcDir"}, description = "Directory with sources exported to xml", defaultValue = "")
    private String srcDirName;

    @Option(names = {"-P", "--projectDir"}, description = "Directory with project", defaultValue = "")
    private String projectDirName;

    @Option(names = {"-r", "--removeSupport"}, description = "Remove support values: ${COMPLETION-CANDIDATES}. Default - ${DEFAULT-VALUE}", defaultValue = "NONE")
    private SupportVariant removeSupport;

    @Option(names = {"--includeDirs"},
            description = "Список каталогов (через запятую), для которых необходимо строить покрытие",
            split = ",",
            defaultValue = "")
    private List<String> includeDirs;

    @Option(names = {"--excludeDirs"},
            description = "Список каталогов, которые необходимо исключить",
            split = ",",
            defaultValue = "")
    private List<String> excludeDirs;

    public List<String> getIncludeDirs() { return includeDirs; }
    public List<String> getExcludeDirs() { return excludeDirs; }

    private void updatePaths() {
        if (projectDirName.isEmpty() && !srcDirName.isEmpty()) {
            // for backward compatibility
            projectDirName = srcDirName;
            srcDirName = "";
        }
    }

    public String getSrcDirName() {
        updatePaths();
        return srcDirName;
    }

    public void setSrcDirName(String srcDirName) {
        this.srcDirName = srcDirName;
    }

    public String getProjectDirName() {
        updatePaths();
        return projectDirName;
    }

    public void setProjectDirName(String projectDirName) {
        this.projectDirName = projectDirName;
    }

    public SupportVariant getRemoveSupport() {
        return removeSupport;
    }

    public void setRemoveSupport(SupportVariant removeSupport) {
        this.removeSupport = removeSupport;
    }

    public boolean isRawMode() {
        return getSrcDirName().isEmpty()
                && getProjectDirName().isEmpty();
    }
}
