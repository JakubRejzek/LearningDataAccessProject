// JavaScript Document   

var tableBody = document.getElementById("table_data_body");
var webPageDataP = "";
var webPageData = "";
var isTheRTableShown = false;
   getTextFromWebpage(0,"https://webapi.developers.erstegroup.com/api/csas/public/sandbox/v2/rates/exchangerates?web-api-key=c52a0682-4806-4903-828f-6cc66508329e");
   getTextFromWebpage(1,"/currentExchangeRates");    
      function debug ()
      { 
      //loads raw internet data
      if(!isTheRTableShown)
       {
       isTheRTableShown = true;
      tableBody.innerHTML =  changeJaDataIntoUseableForm(webPageDataP); 
       // tableBody.innerHTML = webPageDataP;
       }
       else
       {
         isTheRTableShown = false;
         tableBody.innerHTML = "";
         //update data
         getTextFromWebpage(0,"https://webapi.developers.erstegroup.com/api/csas/public/sandbox/v2/rates/exchangerates?web-api-key=c52a0682-4806-4903-828f-6cc66508329e");
       }
      }
        
    function getDataIntoTable() {
    //load data from API request
              if(!isTheRTableShown)
              {
              tableBody.innerHTML =  changeJaDataIntoUseableForm(webPageData);
              isTheRTableShown = true;
              }
              else
              {
              isTheRTableShown = false;
              tableBody.innerHTML = "";
              //update data
              getTextFromWebpage(1,"/currentExchangeRates?usedb=true");
              }

             
              
        }
        function changeJaDataIntoUseableForm(data)
        {
             var processedData = data.replaceAll("[", "");
             processedData = processedData.replaceAll("}]","</th></tr>");
             
             processedData = processedData.replaceAll("{", "<tr>");
             processedData = processedData.replaceAll("},", "</th></tr>");
             processedData = processedData.replaceAll("}", "</tr>");
             
             //Can be replaced with a good regex expression of this kind: /"*{4,10}":/
             //But I didn�t find a way to realize it.
             processedData = processedData.replaceAll("shortName", "<th>");
             processedData = processedData.replaceAll("validFrom", "<th>");
             processedData = processedData.replaceAll("name", "<th>");
             processedData = processedData.replaceAll("country", "<th>");
             processedData = processedData.replaceAll("amount", "<th>");
             processedData = processedData.replaceAll("valBuy", "<th>");
             processedData = processedData.replaceAll("valSell", "<th>");
             processedData = processedData.replaceAll("valMid", "<th>");
             processedData = processedData.replaceAll("currBuy", "<th>");
             processedData = processedData.replaceAll("currSell", "<th>");
             processedData = processedData.replaceAll("currMid", "<th>");
             processedData = processedData.replaceAll("move", "<th>");
             processedData = processedData.replaceAll("cnbMid", "<th>");
             processedData = processedData.replaceAll("version", "<th>");
             processedData = processedData.replaceAll(":", "");
             processedData = processedData.replaceAll("\"", "");
//----------------------------------------------------------------------------------
             processedData = processedData.replaceAll(",", "</th>");     
             
              return processedData;
        }
        
        async function getTextFromWebpage(source, file) 
        {
          let fetchedPage = await fetch(file);
          var myText = await fetchedPage.text();
          if(source === 0)
          {
             webPageDataP = myText;
          }
          else
          {
             webPageData = myText;
          }
          
        }
        
