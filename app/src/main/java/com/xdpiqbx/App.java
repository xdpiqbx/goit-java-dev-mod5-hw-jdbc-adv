/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.xdpiqbx;

import com.xdpiqbx.db.services.DatabaseInitService;
import com.xdpiqbx.db.services.DatabasePopulateService;
import com.xdpiqbx.db.services.DatabaseQueryService;

public class App {
    public static void main(String[] args) {
        DatabaseInitService.initDatabase();
        DatabasePopulateService.populateDatabase();
        DatabaseQueryService.printResult();
    }
}
