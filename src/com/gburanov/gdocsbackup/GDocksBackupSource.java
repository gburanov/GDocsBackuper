package com.gburanov.gdocsbackup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

public class GDocksBackupSource implements BackupSource {
  Drive drive;
  
  
  public GDocksBackupSource(Drive drive) throws IOException
  {
    this.drive = drive;
    List<File> result = new ArrayList<File>();
    Files.List request = drive.files().list();
    FileList files = request.execute();
    result.addAll(files.getItems());

    for(File file: result) {
      System.out.println(file.getDownloadUrl());
    }
  }

}
