package herotozero.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import herotozero.dao.EmissionDAO;
import herotozero.model.Emission;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class DataImport {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        InputStream is = DataImport.class.getResourceAsStream("/co2_emissions_data.json");
        if (is == null) {
            System.err.println("JSON Datei nicht gefunden! Pfad prüfen!");
            return;
        }

        List<Map<String, Object>> entries = mapper.readValue(is, new TypeReference<>() {
        });
        System.out.println("Einträge in JSON-Datei: " + entries.size());
        EmissionDAO dao = new EmissionDAO();

       for (Map<String, Object> entry : entries) {
            try {
        System.out.println("Verarbeite: " + entry);

        int year = Integer.parseInt(entry.get("year").toString());
        String country = (String) entry.get("country");
        String company = (String) entry.get("company");
        String sector = (String) entry.get("sector");

        Object emissionsRaw = entry.get("emissions_mt");
        if (emissionsRaw == null) {
            System.err.println("Kein emissions_mt vorhanden: " + entry);
            continue;
        }

        double emissions = Double.parseDouble(emissionsRaw.toString());

        Emission emission = new Emission(country, company, sector, emissions, year);
        dao.save(emission);

    } catch (Exception e) {
        System.err.println("Fehler bei diesem Datensatz: " + entry);
        e.printStackTrace();
    }
}

        System.out.println("Import completed!");
    }
}