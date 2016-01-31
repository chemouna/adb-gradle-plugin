package com.mounacheikhna.screenshots.adb

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.TaskAction

/**
 * Created by cheikhnamouna on 1/31/16.
 */
public class LaunchTask extends DefaultTask implements LaunchSpec {

  private String deviceType
  private String locale

  @TaskAction
  void performTask() {
    String[] localeParts = locale.split("_")
    List<String> execArgs = ["adb", "shell", "'setprop", "persist.sys.language", "${localeParts[0]}", ";",
                              "setprop", "persist.sys.country", "${localeParts[1]}", ";", "stop", ";",
                              "sleep", "5", ";", "start'"]
   //adb -s emulator-5554 shell 'setprop persist.sys.language it; setprop persist.sys.country IT; stop; sleep 5;start'
   project.tasks.create("launch", Exec) {
      workingDir .
      commandLine execArgs
    }.execute()
  }

  @Override
  void deviceType(String deviceType) {
    this.deviceType = deviceType
  }

  @Override
  void locale(String locale) {
    this.locale = locale
  }
}
