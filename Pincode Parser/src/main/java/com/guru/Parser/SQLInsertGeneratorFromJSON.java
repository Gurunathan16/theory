package com.guru.Parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class SQLInsertGeneratorFromJSON
{
    public static void begin()
    {
        System.out.println("Beginning");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = null;
        try {
            root = objectMapper.readTree(new File("pinCode.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JsonNode records = root.get("records");

        Set<String> states = new LinkedHashSet<>();
        Set<String> districts = new LinkedHashSet<>();
        Set<String> pinCodes = new LinkedHashSet<>();

        Map<String, Integer> stateMap = new HashMap<>();
        Map<String, Integer> districtMap = new HashMap<>();
        Set<Integer> pinCodeSet = new HashSet<>();

        Integer stateId = 1;
        Integer districtId = 1;
        Integer pinCodeId = 1;
        Integer pinCode;

        String state;
        String district;
        String districtKey;


        for(JsonNode record : records)
        {
            state = record.get("statename").asText().trim();
            district = record.get("district").asText().trim();
            pinCode = record.get("pincode").asInt();
            districtKey = district + "|" + state;

            if(!stateMap.containsKey(state))
            {
                stateMap.put(state, stateId++);

                states.add("INSERT INTO state VALUES (" + (stateId - 1) + ", '" + state + "');");
            }

            if(!districtMap.containsKey(districtKey))
            {
                districtMap.put(districtKey, districtId++);

                districts.add("INSERT INTO district VALUES (" + (districtId - 1) + ", '" + district + "', " + stateMap.get(state) + ");");
            }

            if(!pinCodeSet.contains(pinCode))
            {
                pinCodes.add("INSERT INTO pinCode VALUES (" + pinCodeId++ + ", " + pinCode + ", " + districtMap.get(districtKey) + ");");

                pinCodeSet.add(pinCode);
            }
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data.sql")))
        {
            for(String line : states)
                bufferedWriter.write(line + "\n");
            bufferedWriter.flush();

            for(String line : districts)
                bufferedWriter.write(line + "\n");
            bufferedWriter.flush();

            for(String line : pinCodes)
                bufferedWriter.write(line + "\n");
            bufferedWriter.flush();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Ended");

    }
}
