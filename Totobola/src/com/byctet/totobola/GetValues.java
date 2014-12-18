package com.byctet.totobola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class GetValues {
  static HashMap<String,String> fromFile( String fileName) throws Exception {
    HashMap<String,String> values = new HashMap<String,String>();
    BufferedReader br = new BufferedReader( new FileReader( new File( fileName)));
    int idx;
    String line;
    while( (line=br.readLine())!=null) {
      if( !line.startsWith( "#") && !"".equals( line.trim())) {
        if( (idx=line.indexOf( "="))==-1) {
          System.out.println( "Linha inv�lida: " + line);
          System.out.println( "Deve ter a estrutura vari�vel=valor");
          System.exit( 1);
        }
        else {
          values.put( line.substring( 0, idx), line.substring( idx+1));
        }
      }
    }
    br.close();
    return values;
  }
}
