package com.byctet.totobola;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ApplyFilter {

  public static boolean qtdSimb( char[] grupo, char[] chave, String simb, String limits) throws Exception {
    StringTokenizer st = new StringTokenizer( limits, "*");
    int ini = Integer.parseInt( st.nextToken());
    int end = Integer.parseInt( st.nextToken());
    int qtd = 0;
    for( int i = 0 ; i < chave.length ; i++) {
      for( int j = 0 ; j < simb.length() ; j++) {
        if( chave[i] == simb.charAt( j)) {
          qtd++;
        }
      }
    }
    return (qtd >= ini && qtd <=end) ? true : false;
  }

  public static boolean simbSeg( char[] grupo, char[] chave, String simb, String limits) throws Exception {
    StringTokenizer st = new StringTokenizer( limits, "*");
    int ini = Integer.parseInt( st.nextToken());
    int end = Integer.parseInt( st.nextToken());
    char simbolo;
    int consec = 0;
    int maxconsec = 0;
    boolean found = false;
    for( int i = 0 ; i < chave.length ; i++) {
      found = false;
      for( int j = 0 ; j < simb.length() ; j++) {
        simbolo = simb.charAt( j);
        if( chave[i] == simbolo) {
          found = true;
        }
      }
      if( found) {
        consec = consec + 1;
        if( consec > 1 && consec > maxconsec) {
          maxconsec = consec;
        }
      }
      else {
        consec = 0;
      }
    }
    return (maxconsec >= ini && maxconsec <=end) ? true : false;
  }

  public static boolean nconsec( char[] grupo, char[] chave, String limits) throws Exception {
    StringTokenizer st = new StringTokenizer( limits, "*");
    int ini = Integer.parseInt( st.nextToken());
    int end = Integer.parseInt( st.nextToken());
    int nconsec = 0;
    for( int i = 0 ; i < chave.length - 1 ; i++) {
      if( chave[i] != chave[i + 1]) {
        nconsec = nconsec + 1;
      }
    }
    return (nconsec >= ini && nconsec <=end) ? true : false;
  }

  public static boolean figuras( char[] grupo, char[] chave, String figs) throws Exception {
    StringTokenizer st = new StringTokenizer( figs, "*");
    int[][] figuras = new int[st.countTokens()][3];
    int f=0;
    while( st.hasMoreTokens()) {
      String fig = st.nextToken();
      StringTokenizer stf = new StringTokenizer( fig, "-");
      figuras[f][0] = Integer.parseInt( stf.nextToken());
      figuras[f][1] = Integer.parseInt( stf.nextToken());
      figuras[f][2] = Integer.parseInt( stf.nextToken());
      f++;
    }
    int um=0, xis=0, dois=0;
    for( int i = 0 ; i < chave.length ; i++) {
            if( chave[i] == '1') um++;
      else  if( chave[i] == 'X') xis++;
      else  if( chave[i] == '2') dois++;
    }
    for( int i = 0 ; i < figuras.length ; i++) {
      if( figuras[i][0] == um && figuras[i][1] == xis && figuras[i][2] == dois) {
        return true;
      }
    }
    return false;
  }

  public static void filter( char[] grupo, char[] chave, HashMap<String,String> values, BufferedWriter bw, boolean ok[]) throws Exception {
    int filter=0;
    bw.write( new String( chave));
    if( values.get( "1Qtd")!=null && !( ok[ filter++] = qtdSimb( grupo, chave, "1", values.get( "1Qtd")))) {
      bw.write( "->1Qtd");
    }
    if( values.get( "XQtd")!=null && !( ok[ filter++] = qtdSimb( grupo, chave, "X", values.get( "XQtd")))) {
      bw.write( "->XQtd");
    }
    if( values.get( "2Qtd")!=null && !( ok[ filter++] = qtdSimb( grupo, chave, "2", values.get( "2Qtd")))) {
      bw.write( "->2Qtd");
    }
    if( values.get( "VQtd")!=null && !( ok[ filter++] = qtdSimb( grupo, chave, "2X", values.get( "VQtd")))) {
      bw.write( "->VQtd");
    }
    if( values.get( "1Seg")!=null && !( ok[ filter++] = simbSeg( grupo, chave, "1", values.get( "1Seg")))) {
      bw.write( "->1Seg");
    }
    if( values.get( "XSeg")!=null && !( ok[ filter++] = simbSeg( grupo, chave, "X", values.get( "XSeg")))) {
      bw.write( "->XSeg");
    }
    if( values.get( "2Seg")!=null && !( ok[ filter++] = simbSeg( grupo, chave, "2", values.get( "2Seg")))) {
      bw.write( "->2Seg");
    }
    if( values.get( "VSeg")!=null && !( ok[ filter++] = simbSeg( grupo, chave, "X2", values.get( "VSeg")))) {
      bw.write( "->VSeg");
    }
    if( values.get( "NConsec")!=null && !( ok[ filter++] = nconsec( grupo, chave, values.get( "NConsec")))) {
      bw.write( "->NConsec");
    }
    if( values.get( "Figuras")!=null && !( ok[ filter++] = figuras( grupo, chave, values.get( "Figuras")))) {
      bw.write( "->Figuras");
    }
    bw.write( "\n");
  }

  public static void main( String[] args) throws Exception {
    String fileBetConditions = args[0]; //Ficheiro de condições
    String fileToFilter = args[1];  //Ficheiro de chaves a filtrar
    String fileFiltered = args[2]; //Ficheiro de chaves filtradas
    String fileResult = args[3]; //Ficheiro de chaves resultantes

    HashMap<String,String> values = GetValues.fromFile( fileBetConditions);

    if( values.get( "1Qtd")!=null) System.out.println( "qtdSimb::1Qtd" + ":" + values.get( "1Qtd"));
    if( values.get( "XQtd")!=null) System.out.println( "qtdSimb::XQtd" + ":" + values.get( "XQtd"));
    if( values.get( "2Qtd")!=null) System.out.println( "qtdSimb::2Qtd" + ":" + values.get( "2Qtd"));
    if( values.get( "VQtd")!=null) System.out.println( "qtdSimb::VQtd" + ":" + values.get( "VQtd"));

    if( values.get( "1Seg")!=null) System.out.println( "simbSeg::1Seg" + ":" + values.get( "1Seg"));
    if( values.get( "XSeg")!=null) System.out.println( "simbSeg::XSeg" + ":" + values.get( "XSeg"));
    if( values.get( "2Seg")!=null) System.out.println( "simbSeg::2Seg" + ":" + values.get( "2Seg"));

    if( values.get( "NConsec")!=null) System.out.println( "NConsec" + ":" + values.get( "NConsec"));

    if( values.get( "Figuras")!=null) System.out.println( "Figuras" + ":" + values.get( "Figuras"));

    int maxFilters = 50; // até 50 testes
    boolean ok[] = new boolean[maxFilters];
    char grupo[] = new char[13];
    char chave[] = new char[13];
    BufferedWriter bf = new BufferedWriter( new FileWriter( new File( fileFiltered)));
    BufferedWriter bw = new BufferedWriter( new FileWriter( new File( fileResult)));
    BufferedReader br = new BufferedReader( new FileReader( new File( fileToFilter)));
    int numOfFiltered = 0;
    int numOfOk = 0;
    String line;
    while( (line=br.readLine())!=null) {
      for( int i=0 ; i<line.length() ; i++) {
        chave[i] = line.charAt( i);
      }
      //initialize ok filters
      for( int i=0 ; i<maxFilters ; i++) {
        ok[i] = true;
      }
      filter( grupo, chave, values, bf, ok);
      //write good chave to result file and initialize ok filters
      boolean result = true;
      for( int i=0 ; i<maxFilters ; i++) {
        if( ok[i]==false) result = false;
        ok[i] = true;
      }
      if( result) {
        numOfOk++;
        bw.write( line + "\n");
      }
      else {
        numOfFiltered++;
      }
    }
    br.close();
    bw.close();
    bf.close();
    System.out.println( "Numero de chaves filtradas  :" + numOfFiltered);
    System.out.println( "Numero de chaves resultantes:" + numOfOk);
  }

}
