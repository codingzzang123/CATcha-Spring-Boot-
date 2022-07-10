package com.ib.cat.utils;

import com.ib.cat.dto.ContentsDto;
import com.ib.cat.dto.main.SearchCountDTO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
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
    @Autowired
    SearchCountDTO scd;

    public List<ContentsDto> getMovieList(int page, String query){
        String apiURL =API_URL+ "search/movie?api_key=" + KEY +"&language=ko-KR&query="+query+"&page="+page;
        List<ContentsDto> movieList = null;


        try {
            URL url = new URL(apiURL);
            System.out.println("URL url = "+ url);
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
            }
        }catch(Exception e) {

        }
        return movieList;
    }

    public List<ContentsDto> getTvList(int page,String query){
        String apiURL =API_URL+ "search/tv?api_key=" + KEY +"&language=ko-KR&query="+query+"&page="+page;
        List<ContentsDto> tvList = null;

        try {
            URL url = new URL(apiURL);
            System.out.println("URL url = "+ url);
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
            }
        }catch(Exception e) {

        }
        return tvList;
    }

//    public List<MainDTO> getBoardList(int page,String query){
//        int start = (page-1) * 20 + 1;
//        int end = page * 20;
//
//        SearchBoardDTO sbd = new SearchBoardDTO(start,end,query);
//
//        List<MainDTO> boardList = dao.searchBoard(sbd);
//
//        return boardList;
//    }

    public SearchCountDTO contents(String query){
        String apiMovieURL =API_URL+ "search/movie?api_key=" + KEY +"&language=ko-KR&query="+query;
        String apiTvURL =API_URL+ "search/tv?api_key=" + KEY +"&language=ko-KR&query="+query;

        try {
            URL url = new URL(apiMovieURL);
            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            String result = bf.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            scd.setMovie(Integer.parseInt(jsonObject.get("total_results").toString()));

            url = new URL(apiTvURL);

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();
            jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(result);
            scd.setTv(Integer.parseInt(jsonObject.get("total_results").toString()));

        }catch(Exception e) {
            e.printStackTrace();
        }

//        scd.setBoard(dao.searchBoardCount(query));
        return scd;
    }
}
