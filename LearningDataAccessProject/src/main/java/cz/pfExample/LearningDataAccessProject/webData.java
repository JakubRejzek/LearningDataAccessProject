package cz.pfExample.LearningDataAccessProject;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

//@Service
@RestController
@RequestMapping("/currentExchangeRates")
public class webData {
    public String currentlySavedExchangeData = "";
    private RestTemplate myrestTemplate = new RestTemplate();



        @GetMapping
        public String returnSoLdata(@RequestParam(value = "usedb") boolean usedb)
        {
            if (usedb)
            {
                return  currentlySavedExchangeData;
            }
            else
            {
                currentlySavedExchangeData = getDataFromBank();
                saveDataToTXTFile(currentlySavedExchangeData);
                return  currentlySavedExchangeData;
            }
        }
        public List<String> readFileData(String dataPath)
        {
            List<String> text = new ArrayList<String>();

            try {
                BufferedReader reader = new BufferedReader(new FileReader(dataPath));
           String s= "";
           while ((s=reader.readLine()) != null)
           {
              text.add(reader.readLine());
               //upon each line added to s
           }
                reader.close();
                System.out.println(text);
                return text;
            }
            catch (Exception e)
            {
                return text;
            }

        }
        public void saveDataToTXTFile(String dataToSave)
        {
            String pathToFile = "src/main/resources/private/data.txt";
            //readFileData(pathToFile);
            try {
                List<String> previousText = readFileData(pathToFile);
                BufferedWriter bw = new BufferedWriter(new FileWriter(pathToFile));
                for (int i =0; i< previousText.size();i++)
                {
                    bw.write(previousText.get(i));
                }
                bw.write("\n"+dataToSave);
                bw.close();
            }
            catch (Exception e)
            {
                System.out.println("Exception cause: "+e.getCause());
            }
        }


    @Scheduled(initialDelay = 5000L, fixedDelayString = "PT24H")
    void someTimer()
    {
        currentlySavedExchangeData = myrestTemplate.getForObject("https://webapi.developers.erstegroup.com/api/csas/public/sandbox/v2/rates/exchangerates?web-api-key=c52a0682-4806-4903-828f-6cc66508329e",String.class);
        System.out.println("We are saving data: \n " + currentlySavedExchangeData);
    }
    String getDataFromBank()
    {
        return myrestTemplate.getForObject("https://webapi.developers.erstegroup.com/api/csas/public/sandbox/v2/rates/exchangerates?web-api-key=c52a0682-4806-4903-828f-6cc66508329e",String.class);
        //System.out.println("We are saving data: \n " + currentlySavedExchangeData);
    }
}

