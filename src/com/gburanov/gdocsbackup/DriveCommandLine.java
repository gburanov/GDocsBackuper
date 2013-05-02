package com.gburanov.gdocsbackup;

import com.google.api.services.drive.Drive;
import java.io.IOException;

public class DriveCommandLine
{

  
  public static void main(String[] args) throws IOException
  {
    DocsAutorization auth = new DocsAutorization();
    //Create a new authorized API client
    Drive drive = auth.getDrive();
    BackupSource source = new GDocksBackupSource(drive);

  }


}
