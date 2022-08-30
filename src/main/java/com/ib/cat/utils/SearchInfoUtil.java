package com.ib.cat.utils;

import com.ib.cat.dto.media.ContentsDto;
import com.ib.cat.dto.main.SearchCountDto;
import com.ib.cat.repository.BoardRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SearchInfoUtil {

    private static final String KEY = "e8b937d602d4a3d3bda9a5be4c9a6a44";
    private static final String API_URL = "https://api.themoviedb.org/3/";

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final String date = "0001-01-01";


    public List<ContentsDto> getMovieList(int page, String query){

        String apiURL =API_URL+ "search/movie?api_key=" + KEY +"&language=ko-KR&query="+query+"&page="+page;
        List<ContentsDto> movieList = null;

        try {
            URL url = new URL(apiURL);
            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));


            String result = bf.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONArray list = (JSONArray) jsonObject.get("results");

            movieList = new ArrayList<ContentsDto>();
            for (int j = 0; j < list.size(); j++) {
                ContentsDto vo = new ContentsDto();
                JSONObject contents = (JSONObject) list.get(j);

                vo.setContentsNum(Integer.parseInt(contents.get("id").toString()));
                vo.setOverview(contents.get("overview").toString());
                vo.setTitle(contents.get("title").toString());
                vo.setVoteAverage(Float.parseFloat(String.valueOf(contents.get("vote_average"))));
                if(contents.get("release_date") == null || contents.get("release_date").equals("")) {
                    vo.setReleaseDate(dateFormat.parse(date));
                } else {
                    Date releaseDate = dateFormat.parse((String)contents.get("release_date"));
                    vo.setReleaseDate(releaseDate);
                }
                if (contents.get("poster_path") == null || contents.get("poster_path").toString().equals("")) {
                    vo.setPosterPath("");
                } else {
                    vo.setPosterPath(contents.get("poster_path").toString());
                }
                movieList.add(vo);

                if(bf != null) bf.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return movieList;
    }

    public List<ContentsDto> getTvList(int page,String query){

        String apiURL =API_URL+ "search/tv?api_key=" + KEY +"&language=ko-KR&query="+query+"&page="+page;
        List<ContentsDto> tvList = null;

        try {
            URL url = new URL(apiURL);
            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            String result = bf.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONArray list = (JSONArray) jsonObject.get("results");

            tvList = new ArrayList<ContentsDto>();
            for (int j = 0; j < list.size(); j++) {
                ContentsDto vo = new ContentsDto();
                JSONObject contents = (JSONObject) list.get(j);

                vo.setContentsNum(Integer.parseInt(contents.get("id").toString()));
                vo.setOverview(contents.get("overview").toString());
                vo.setTitle(contents.get("name").toString());
                vo.setVoteAverage(Float.parseFloat(String.valueOf(contents.get("vote_average"))));
                if (contents.get("poster_path") == null || contents.get("poster_path").toString().equals("")) {
                    vo.setPosterPath("");
                } else {
                    vo.setPosterPath(contents.get("poster_path").toString());
                }
                if(contents.get("first_air_date") == null || contents.get("first_air_date").equals("")) {
                    vo.setReleaseDate(dateFormat.parse(date));
                } else {
                    Date releaseDate = dateFormat.parse((String)contents.get("first_air_date"));
                    vo.setReleaseDate(releaseDate);
                }

                tvList.add(vo);

                if(bf != null) bf.close();
            }
        }catch(Exception e) {

        }
        return tvList;
    }

    public int countMovies(String query){
        String apiURL = API_URL+ "search/movie?api_key=" + KEY +"&language=ko&query="+query;
        int result;
        try {
            URL url = new URL(apiURL);
            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            String page = bf.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(page);
            result = Integer.parseInt(jsonObject.get("total_results").toString());

            bf.close();
        }catch(Exception e) {
            e.printStackTrace();
            result = 0;
        }
        return  result;
    }

    public int countTVs(String query){
        String apiURL = API_URL+ "search/tv?api_key=" + KEY +"&language=ko&query="+query;
        int result=0;
        try {
            URL url = new URL(apiURL);
            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            String page = bf.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(page);
            result = Integer.parseInt(jsonObject.get("total_results").toString());

            if(bf != null) bf.close();

        }catch(Exception e) {
            e.printStackTrace();
            result = 0;
        }
        return  result;
    }

}
