package de.sfin.connector;

import java.io.File;
import io.jsondb.JsonDBTemplate;

public class JsonDBConnector {

  String dbFilesLocation = "."+File.separator+"JsonDBInstances";

  //Java package name where POJO's are present
  String baseScanPackage = "de.sfin.model";

  public JsonDBTemplate createJsonDBTemplate(){
    return new JsonDBTemplate(dbFilesLocation, baseScanPackage);
  }




}
