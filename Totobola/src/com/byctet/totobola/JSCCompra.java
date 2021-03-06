package com.byctet.totobola;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;
import org.xml.sax.helpers.AttributesImpl;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.html.InputElementFactory;

public class JSCCompra {

  public static void addFormInput( HtmlForm form, String type, String name, String value) throws Exception {
    AttributesImpl attributes = new AttributesImpl();
    attributes.addAttribute( "", "", "type" , "CDATA", type );
    attributes.addAttribute( "", "", "name" , "CDATA", name );
    attributes.addAttribute( "", "", "value", "CDATA", value);
    HtmlInput newInput = (HtmlInput) InputElementFactory.instance.createElement( form.getPage(), "input", attributes);
    form.appendChild( newInput);
  }

  public static HtmlPage addAposta( WebClient webClient, ArrayList<String> boletim, String s14c, String s14f) throws Exception {
    HtmlPage totobolaPage = webClient.getPage( "https://www.jogossantacasa.pt/web/JogarTotobola/");
    String totobolaPageTxt = totobolaPage.asXml();
    System.out.println( totobolaPageTxt);

    List<HtmlForm> totobolaPageForms = totobolaPage.getForms();
    for( int i=0 ; i<totobolaPageForms.size() ; i++) {
      System.out.println( totobolaPageForms.get( i));
    }
    final HtmlForm carrinhoForm = totobolaPageForms.get( 5);

    //Board.channel
    String boardChannel = "Board.channel = ";
    int idxIni = totobolaPageTxt.indexOf( boardChannel) + boardChannel.length();
    int idxEnd = totobolaPageTxt.indexOf( ";", idxIni);
    String value = totobolaPageTxt.substring( idxIni, idxEnd);
    System.out.println( boardChannel + value);
    addFormInput( carrinhoForm, "hidden", "hidChannel", value);

    //Board.game_id
    String boardGameId = "Board.game_id = ";
    idxIni = totobolaPageTxt.indexOf( boardGameId) + boardGameId.length();
    idxEnd = totobolaPageTxt.indexOf( ";", idxIni);
    value = totobolaPageTxt.substring( idxIni, idxEnd);
    System.out.println( boardGameId + value);
    addFormInput( carrinhoForm, "hidden", "hidGameId", value);

    //Board.contest_id
    String boardContestId = "Board.contest_id = ";
    idxIni = totobolaPageTxt.indexOf( boardContestId) + boardContestId.length();
    idxEnd = totobolaPageTxt.indexOf( ".", idxIni);
    value = totobolaPageTxt.substring( idxIni, idxEnd);
    System.out.println( boardContestId + value);
    addFormInput( carrinhoForm, "hidden", "Contest", value);

    //Board.game_name = 'Totobola';
    addFormInput( carrinhoForm, "hidden", "hidName", "Totobola");

    //Joker - N�o
    addFormInput( carrinhoForm, "hidden", "hidJoker", "N");
    addFormInput( carrinhoForm, "hidden", "hidNLuckyDipJoker", "0");

    //Apostas
    addFormInput( carrinhoForm, "hidden", "hidType", "S");    //jogos simples
    System.out.println( "hidWager=" + boletim.size());
    addFormInput( carrinhoForm, "hidden", "hidNWager", "" + boletim.size());  //qtd de chaves
    addFormInput( carrinhoForm, "hidden", "hidNDouble", "0"); //qtd de duplas
    addFormInput( carrinhoForm, "hidden", "hidNTriple", "0"); //qtd triplas

    for( int i=0 ; i<boletim.size() ; i++) {
      System.out.println( "hidWager" + (i+1) + "=" + boletim.get( i));
      addFormInput( carrinhoForm, "hidden", "hidWager" + (i+1) , boletim.get( i));
    }
    for( int i=boletim.size() ; i<10 ; i++) {
      System.out.println( "hidWager" + (i+1) + "=" + "0,0,0,0,0,0,0,0,0,0,0,0,0");
      addFormInput( carrinhoForm, "hidden", "hidWager" + (i+1) , "0,0,0,0,0,0,0,0,0,0,0,0,0");
    }
    /*
    addFormInput( carrinhoForm, "hidden", "hidWager1" , "1,2,4,1,2,4,1,2,4,1,2,4,1");
    addFormInput( carrinhoForm, "hidden", "hidWager2" , "1,2,4,1,2,4,1,2,4,1,2,4,1");
    addFormInput( carrinhoForm, "hidden", "hidWager3" , "1,2,4,1,2,4,1,2,4,1,2,4,1");
    addFormInput( carrinhoForm, "hidden", "hidWager4" , "1,2,4,1,2,4,1,2,4,1,2,4,1");
    addFormInput( carrinhoForm, "hidden", "hidWager5" , "1,2,4,1,2,4,1,2,4,1,2,4,1");
    addFormInput( carrinhoForm, "hidden", "hidWager6" , "1,2,4,1,2,4,1,2,4,1,2,4,1");
    addFormInput( carrinhoForm, "hidden", "hidWager7" , "1,2,4,1,2,4,1,2,4,1,2,4,1");
    addFormInput( carrinhoForm, "hidden", "hidWager8" , "1,2,4,1,2,4,1,2,4,1,2,4,1");
    addFormInput( carrinhoForm, "hidden", "hidWager9" , "0,0,0,0,0,0,0,0,0,0,0,0,0");
    addFormInput( carrinhoForm, "hidden", "hidWager10", "0,0,0,0,0,0,0,0,0,0,0,0,0");
    */
    //Super14
    System.out.println( "hidSuper141=" + s14c);
    System.out.println( "hidSuper142=" + s14f);
    addFormInput( carrinhoForm, "hidden", "hidSuper141", s14c);
    addFormInput( carrinhoForm, "hidden", "hidSuper142", s14f);

    /*
    hidChannel:1
    hidGameId:6
    Contest:8442
    hidName:Totobola
    hidJoker:N
    hidNLuckyDipJoker:0
    hidType:S
    hidNWager:2
    hidNDouble:0
    hidNTriple:0
    hidWager1:1,1,1,1,1,1,1,1,1,1,1,1,1
    hidWager2:1,1,1,1,1,1,1,1,1,1,1,1,1
    hidWager3:0,0,0,0,0,0,0,0,0,0,0,0,0
    hidWager4:0,0,0,0,0,0,0,0,0,0,0,0,0
    hidWager5:0,0,0,0,0,0,0,0,0,0,0,0,0
    hidWager6:0,0,0,0,0,0,0,0,0,0,0,0,0
    hidWager7:0,0,0,0,0,0,0,0,0,0,0,0,0
    hidWager8:0,0,0,0,0,0,0,0,0,0,0,0,0
    hidWager9:0,0,0,0,0,0,0,0,0,0,0,0,0
    hidWager10:0,0,0,0,0,0,0,0,0,0,0,0,0
    hidSuper141:1
    hidSuper142:1
    */

    //----------------------------------------------------------------------------------------------

    //Colocar a aposta no carrinho
    //Submit button
    addFormInput( carrinhoForm, "submit", "btnAddCart", "AddCart");
    HtmlPage carrinhoPage = ((HtmlSubmitInput)carrinhoForm.getInputByName( "btnAddCart")).click();
    System.out.println( "carrinhoPage");
    System.out.println( carrinhoPage.asXml());
    return carrinhoPage;
  }

  public static void main(String[] args) throws Exception {
    String pusr = args[0];
    String ppwd = args[1];
    String fileBet = args[2];
    String s14c = args[3];
    String s14f = args[4];

         if( s14c.charAt( 0)=='0') s14c = "1";
    else if( s14c.charAt( 0)=='1') s14c = "2";
    else if( s14c.charAt( 0)=='M') s14c = "4";

         if( s14f.charAt( 0)=='0') s14f = "1";
    else if( s14f.charAt( 0)=='1') s14f = "2";
    else if( s14f.charAt( 0)=='M') s14f = "4";

    LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
    java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
    java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
    //WebClient webClient = new WebClient( BrowserVersion.CHROME, "192.168.119.254", 8080);
    WebClient webClient = new WebClient( BrowserVersion.CHROME);
    webClient.getOptions().setJavaScriptEnabled(false);

    //----------------------------------------------------------------------------------------------

    HtmlPage loginPage = webClient.getPage( "https://www.jogossantacasa.pt/web/login/view?frame=true&amp;r=https://www.jogossantacasa.pt/");
    System.out.println( loginPage.asXml());

    final List<HtmlForm> forms = loginPage.getForms();
    final HtmlForm loginForm = forms.get(0);

    ((HtmlTextInput)loginForm.getInputByName("txtUser")).setDefaultValue( pusr);

    String hpwd = "";
    String pwd = "";
    for( int i=0 ; i<ppwd.length() ; i++) {
      HtmlButtonInput btn = loginPage.getElementByName( ""+ppwd.charAt( i));
      hpwd += btn.getAttribute( "id").substring( 1);
      pwd += "*";
    }
    System.out.println( "hpwd="+hpwd);
    System.out.println( "pwd="+pwd);

    ((HtmlHiddenInput)loginForm.getInputByName( "txtPassword")).setDefaultValue( hpwd);
    ((HtmlPasswordInput)loginForm.getInputByName( "password")).setDefaultValue( pwd);


    //----------------------------------------------------------------------------------------------

    HtmlPage initialPage = ((HtmlSubmitInput)loginForm.getInputByName( "next")).click();
    System.out.println( initialPage.asXml());

    //----------------------------------------------------------------------------------------------

    HtmlPage carrinhoPage = null;

    BufferedReader br = new BufferedReader( new FileReader( new File( fileBet)));
    String lineBet, jogo="";
    ArrayList<String> boletim = new ArrayList<String>();
    while( (lineBet=br.readLine())!=null) {
      for( int i=0 ; i<lineBet.length() ; i++) {
        char simbolo = lineBet.charAt( i);
             if( simbolo=='1') if( i>0) jogo += ",1"; else jogo = "1";
        else if( simbolo=='X') if( i>0) jogo += ",2"; else jogo = "2";
        else if( simbolo=='2') if( i>0) jogo += ",4"; else jogo = "4";
      }
      boletim.add( jogo);
      if( boletim.size()==10) {
        carrinhoPage = addAposta( webClient, boletim, s14c, s14f);
        /*
        for( int i=0 ; i<boletim.size() ; i++) {
          System.out.println( "hidWager" + (i+1) + "=" + boletim.get( i));
        }
        */
        boletim.clear();
      }
    }
    if( boletim.size() > 0) {
      carrinhoPage = addAposta( webClient, boletim, s14c, s14f);
    }
    /*
    for( int i=0 ; i<boletim.size() ; i++) {
      System.out.println( "hidWager" + (i+1) + "=" + boletim.get( i));
    }
    for( int i=boletim.size() ; i<10 ; i++) {
      System.out.println( "hidWager" + (i+1) + "=" + "0,0,0,0,0,0,0,0,0,0,0,0,0");
    }
    */
    br.close();

    //----------------------------------------------------------------------------------------------

    //Logout
    //<a href="/web/login/logout" class="linkLogout">
    HtmlAnchor logout = carrinhoPage.getAnchorByHref( "/web/login/logout");
    HtmlPage logoutPage = logout.click();
    System.out.println( "logoutPage");
    System.out.println( logoutPage.asXml());
    
    webClient.closeAllWindows();
  }

}
