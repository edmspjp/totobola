package com.byctet.totobola;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class GeraAll {

  public static int getCombination( char[][] template) {
    int duplas=0;
    int triplas=0;
    for( int i=0 ; i < template.length ; i++) {
      if( template[i].length == 2) duplas++;
      else if( template[i].length == 3) triplas++;
    }
    System.out.println( "Duplas  : " + duplas );
    System.out.println( "Triplas : " + triplas);
    return (int)Math.pow( 2, duplas) * (int)Math.pow( 3, triplas);
  }

  public static void main( String[] args) throws Exception {
    String fileBetConditions = args[0]; //Ficheiro de condições
    String fileResult = args[1]; //Ficheiro de chaves resultantes
    char[][] template;
    HashMap<String,String> values = GetValues.fromFile( fileBetConditions);
    System.out.println( "Aposta=" + values.get( "Aposta"));
    String aposta = values.get( "Aposta");
    StringTokenizer st = new StringTokenizer( aposta, "*");
    template=new char[st.countTokens()][];
    int t=0;
    while( st.hasMoreTokens()) {
      String tok = st.nextToken();
      template[ t]=new char[ tok.length()];
      for( int s=0 ; s<tok.length() ; s++) {
        template[t][s] = tok.charAt( s);
      }
      t++;
    }
    int totalCombinations=getCombination( template);
    System.out.println( "totalCombinations=" + totalCombinations);
    BufferedWriter bw = new BufferedWriter( new FileWriter( new File( fileResult)));
    for( int s1=0 ; s1 < template[0].length ; s1++) {
      for( int s2=0 ; s2 < template[1].length ; s2++) {
        for( int s3=0 ; s3 < template[2].length ; s3++) {
          for( int s4=0 ; s4 < template[3].length ; s4++) {
            for( int s5=0 ; s5 < template[4].length ; s5++) {
              for( int s6=0 ; s6 < template[5].length ; s6++) {
                for( int s7=0 ; s7 < template[6].length ; s7++) {
                  for( int s8=0 ; s8 < template[7].length ; s8++) {
                    for( int s9=0 ; s9 < template[8].length ; s9++) {
                      for( int s10=0 ; s10 < template[9].length ; s10++) {
                        for( int s11=0 ; s11 < template[10].length ; s11++) {
                          for( int s12=0 ; s12 < template[11].length ; s12++) {
                            for( int s13=0 ; s13 < template[12].length ; s13++) {
                              bw.write( ""+
                              template[ 0][ s1] +
                              template[ 1][ s2] +
                              template[ 2][ s3] +
                              template[ 3][ s4] +
                              template[ 4][ s5] +
                              template[ 5][ s6] +
                              template[ 6][ s7] +
                              template[ 7][ s8] +
                              template[ 8][ s9] +
                              template[ 9][s10] +
                              template[10][s11] +
                              template[11][s12] +
                              template[12][s13] +
                              "\n");
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    bw.close();
  }
}
