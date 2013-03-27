package org.everit.jira.timetracker.plugin;

/*
 * Copyright (c) 2011, Everit Kft.
 *
 * All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.security.JiraAuthenticationContext;

/**
 * The Jira Timetracker plugin utils class.
 */
public final class JiraTimetrackerUtil {

    /**
     * Check the issue original estimated time. If null then the original estimated time wasn't specified, else compare
     * the spent time whit the original estimated time.
     * 
     * @param issue
     *            The issue.
     * @return True if not specified, bigger or equals whit spent time else false.
     */
    public static boolean checkIssueEstimatedTime(final MutableIssue issue) {
        Long estimated = issue.getEstimate();
        Long timeSpent = issue.getTimeSpent();
        Status issueStatus = issue.getStatusObject();
        String issueStatusId = issueStatus.getId();
        if (((estimated == null) || (estimated == 0)) && !issueStatusId.equals("6")) {
            return false;
        }
        return true;
    }

    /**
     * Check the user is logged or not.
     * 
     * @return True if we have logged user else false.
     */
    public static boolean isUserLogged() {
        JiraAuthenticationContext authenticationContext = ComponentManager.getInstance()
                .getJiraAuthenticationContext();
        User user = authenticationContext.getLoggedInUser();
        if (user == null) {
            return false;
        }
        return true;
    }
}
