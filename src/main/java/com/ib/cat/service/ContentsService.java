package com.ib.cat.service;

import com.ib.cat.dto.ContentsDto;
import com.ib.cat.dto.CreditsDto;
import com.ib.cat.dto.GenresDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentsService {

    private static final String KEY = "e8b937d602d4a3d3bda9a5be4c9a6a44";
    private static final String API_URL = "https://api.themoviedb.org/3/";
    //API를 통해 추출한 json 형태의 결과값을 저장할 변수
    private String result ="";

    public ContentsDto getSpecificContent(String type, int contentsNum) {
//		System.out.println("getSpecificContent Util 작동 중");
        ContentsDto sContent = null;
        List<String> genreList = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "0001-01-01";//기본값
        try {
            sContent = new ContentsDto();

            URL url = new URL("https://api.themoviedb.org/3/"+type+"/"+contentsNum+"?api_key="+KEY+"&language=ko");
            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject contents = (JSONObject)jsonParser.parse(result);

            ContentsDto vo = new ContentsDto();
            vo.setContentsNum(Integer.parseInt(String.valueOf(contents.get("id"))));
            vo.setContentsType(type);
            vo.setOverview(contents.get("overview").toString());

            //컨텐츠 타입에 따라 파싱 방법 다르게 설정
            if(type.equals("movie")) {
                //시리즈일 경우 release_date를 key로 데이터 파싱
                if(contents.get("release_date") == null || contents.get("release_date").equals("")) {
                    vo.setReleaseDate(dateFormat.parse(date));
                } else {
                    Date releaseDate = dateFormat.parse((String)contents.get("release_date"));
                    vo.setReleaseDate(releaseDate);
                }
                vo.setTitle(contents.get("title").toString());
            } else if (type.equals("tv")) {
                //시리즈인 경우 releaseAirDate를 key로 데이터 파싱
                if(contents.get("first_air_date") == null || contents.get("first_air_date").equals("")) {
                    vo.setReleaseDate(dateFormat.parse(date));
                } else {
                    Date firstAirDate = dateFormat.parse((String)contents.get("first_air_date"));
                    vo.setReleaseDate(firstAirDate);
                }
                //시리즈일 경우 title이 아닌 name을 key로 데이터 파싱
                vo.setTitle(contents.get("name").toString());
            }
            if(contents.get("poster_path") == null || contents.get("poster_path").toString().equals("")) {
                vo.setPosterPath("");
            } else {
                vo.setPosterPath(contents.get("poster_path").toString());
            }

            //runtime
//				String runtime = String.valueOf(contents.get("runtime"));
//				vo.setRuntime(runtime);
            String runtime = String.valueOf(contents.get("runtime"));
            int hour = Integer.parseInt(runtime) / 60;
            int minute = Integer.parseInt(runtime) % 60;
            vo.setHour(hour);
            vo.setMinute(minute);

            //장르
            JSONArray genreListJ = (JSONArray)contents.get("genres");
            List<GenresDto> tmpls = new ArrayList<>();
            for (int k = 0 ; k < genreListJ.size() ; k++) {
                JSONObject tmp = (JSONObject)genreListJ.get(k);
                int tempId = (Integer.parseInt(tmp.get("id").toString()));
                String tempName = tmp.get("name").toString();
                GenresDto gvo =  new GenresDto();
                gvo.setGenreId(tempId);
                gvo.setGenreName(tempName);
                tmpls.add(gvo);

//					GenresVO genresVO = new GenresVO();
//					JSONObject tmp = (JSONObject)genreListJ.get(k); //JSONObject : {"id": ,"name": } 하나!
//					genresVO.setGenreId(Integer.parseInt(tmp.get("id").toString()));
//					genresVO.setGenreName(tmp.get("name").toString());
//					System.out.println(genresVO);
//					vo.setGenresVO(genresVO);
            }
            vo.setLs(tmpls);
            System.out.println(vo.getLs().size());
            for(GenresDto g : vo.getLs())
                System.out.println("id: "+g.getGenreId()+"\n"
                        +"name : "+g.getGenreName());
            System.out.println();
            sContent = vo;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return sContent;
    }

    //특정 페이지의 List 가져오기
    public List<ContentsDto> getInfoPageList(String type, String sortBy, int page) {
        //int pages = getPages(type, sortBy);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "0001-01-01";

        List<ContentsDto> infoList = null;
        List<Integer> genreList = null;

        try {
            infoList = new ArrayList<ContentsDto>();
            URL url = new URL("https://api.themoviedb.org/3/discover/"+type+"?api_key="+KEY
                    +"&language=ko&sort_by="+sortBy+"&include_adult=false&page="+page);
//				System.out.println("MovieData - sortBy: " + sortBy);
//				System.out.println("MovieData - type: "+type);
//				System.out.println("MovieData - page: "+page);
            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);

            JSONArray list = (JSONArray)jsonObject.get("results");

//			int totalPages = Integer.parseInt(String.valueOf(jsonObject.get("total_pages")));
//			int totalResults = Integer.parseInt(String.valueOf(jsonObject.get("total_results")));

            for (int j = 0 ; j < list.size() ; j++) {
                ContentsDto vo = new ContentsDto();
                JSONObject contents = (JSONObject)list.get(j);

                vo.setContentsNum(Integer.parseInt(String.valueOf(contents.get("id"))));
                vo.setContentsType(type);
                vo.setOverview(contents.get("overview").toString());
                vo.setVoteAverage(Float.parseFloat(String.valueOf(contents.get("vote_average"))));
                vo.setPopularity(Float.parseFloat(String.valueOf(contents.get("popularity"))));

                //컨텐츠 타입에 따라 파싱 방법 다르게 설정
                if(type.equals("movie")) {
                    //시리즈일 경우 release_date를 key로 데이터 파싱
                    if(contents.get("release_date") == null || contents.get("release_date").equals("")) {
                        vo.setReleaseDate(dateFormat.parse(date));
                    } else {
                        Date releaseDate = dateFormat.parse((String)contents.get("release_date"));
                        vo.setReleaseDate(releaseDate);
                    }
                    vo.setTitle(contents.get("title").toString());
                } else if (type.equals("tv")) {
                    //시리즈인 경우 releaseAirDate를 key로 데이터 파싱
                    if(contents.get("first_air_date") == null || contents.get("first_air_date").equals("")) {
                        vo.setReleaseDate(dateFormat.parse(date));
                    } else {
                        Date firstAirDate = dateFormat.parse((String)contents.get("first_air_date"));
                        vo.setReleaseDate(firstAirDate);
                    }
                    //시리즈일 경우 title이 아닌 name을 key로 데이터 파싱
                    vo.setTitle(contents.get("name").toString());
                }
                if(contents.get("poster_path") == null || contents.get("poster_path").toString().equals("")) {
                    vo.setPosterPath("");
                } else {
                    vo.setPosterPath(contents.get("poster_path").toString());
                }
                //장르 id를 List<Integer>형태로 저장 -> 장르 비교를 위한 작업
                JSONArray genreListJ = (JSONArray)contents.get("genre_ids");
                genreList = new ArrayList<Integer>();
                for (int k = 0 ; k < genreListJ.size() ; k++) {
                    genreList.add(Integer.parseInt(String.valueOf(genreListJ.get(k))));
                }
                vo.setGenres(genreList);
//					vo.setTotalPages(totalPages);
//					vo.setTotalResults(totalResults);
                infoList.add(vo);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return infoList;

    }
    //	//전체 List 가져오기
    public List<ContentsDto> getInfoList(String type, String sortBy) {

        int pages = getPages(type, sortBy);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "0001-01-01";

        List<ContentsDto> infoList = null;
        List<Integer> genreList = null;

        try {
            infoList = new ArrayList<ContentsDto>();

            for (int i = 1; i <= pages ; i++) {

                URL url = new URL("https://api.themoviedb.org/3/discover/"+type+"?api_key="+KEY
                        +"&language=ko&sort_by="+sortBy+"&include_adult=false&page="+i);

                BufferedReader bf;

                bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

                result = bf.readLine();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
                JSONArray list = (JSONArray)jsonObject.get("results");

                for (int j = 0 ; j < list.size() ; j++) {
                    ContentsDto vo = new ContentsDto();
                    JSONObject contents = (JSONObject)list.get(j);

                    vo.setContentsNum(Integer.parseInt(String.valueOf(contents.get("id"))));
                    vo.setContentsType(type);
                    vo.setOverview(contents.get("overview").toString());
                    vo.setVoteAverage(Float.parseFloat(String.valueOf(contents.get("vote_average"))));
                    vo.setPopularity(Float.parseFloat(String.valueOf(contents.get("popularity"))));

                    //컨텐츠 타입에 따라 파싱 방법 다르게 설정
                    if(type.equals("movie")) {
                        //시리즈일 경우 release_date를 key로 데이터 파싱
                        if(contents.get("release_date") == null || contents.get("release_date").equals("")) {
                            vo.setReleaseDate(dateFormat.parse(date));
                        } else {
                            Date releaseDate = dateFormat.parse((String)contents.get("release_date"));
                            vo.setReleaseDate(releaseDate);
                        }
                        vo.setTitle(contents.get("title").toString());
                    } else if (type.equals("tv")) {
                        //시리즈인 경우 releaseAirDate를 key로 데이터 파싱
                        if(contents.get("first_air_date") == null || contents.get("first_air_date").equals("")) {
                            vo.setReleaseDate(dateFormat.parse(date));
                        } else {
                            Date firstAirDate = dateFormat.parse((String)contents.get("first_air_date"));
                            vo.setReleaseDate(firstAirDate);
                        }
                        //시리즈일 경우 title이 아닌 name을 key로 데이터 파싱
                        vo.setTitle(contents.get("name").toString());
                    }
                    if(contents.get("poster_path") == null || contents.get("poster_path").toString().equals("")) {
                        vo.setPosterPath("");
                    } else {
                        vo.setPosterPath(contents.get("poster_path").toString());
                    }
//					vo.setPage(Integer.parseInt((String)jsonObject.get("page")));
                    vo.setPage(i);

                    //장르 id를 List<Integer>형태로 저장 -> 장르 비교를 위한 작업
                    JSONArray genreListJ = (JSONArray)contents.get("genre_ids");
                    genreList = new ArrayList<Integer>();
                    for (int k = 0 ; k < genreListJ.size() ; k++) {
                        genreList.add(Integer.parseInt(String.valueOf(genreListJ.get(k))));
                    }
                    vo.setGenres(genreList);
                    infoList.add(vo);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return infoList;
    }


    public List<String> getImages(String type, int id) {
        //이미지 주소 - String
        List<String> imageList = null;

        try {
            imageList = new ArrayList<String>();
            String apiURL = "https://api.themoviedb.org/3/" + type + "/" + id + "/images?api_key=" + KEY;
            URL url = new URL(apiURL);
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONArray list = (JSONArray)jsonObject.get("backdrops");

            for (int j = 0 ; j < list.size() ; j++) {
                String filePath = new String();
                JSONObject images = (JSONObject)list.get(j);
                filePath = images.get("file_path").toString();
                imageList.add(filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageList;
    }

    public List<CreditsDto> getCredits(String type, int id, String kind) {
        List<CreditsDto> creditList = null;

        try {
            creditList = new ArrayList<CreditsDto>();
            String apiURL = "https://api.themoviedb.org/3/" + type + "/" + id + "/credits?api_key=" + KEY;
            URL url = new URL(apiURL);

            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONArray list = (JSONArray)jsonObject.get(kind);

            /*배우 목록*/
            if(kind.equals("cast")) {
                for (int i = 0 ; i < list.size() ; i++) {
                    JSONObject credits = (JSONObject)list.get(i);
                    CreditsDto vo = new CreditsDto();
                    if (credits.get("known_for_department").equals("Acting")) {
                        vo.setName(credits.get("name").toString());
                        if(credits.get("profile_path") == null) {
                            vo.setProfilePath("");
                        } else {
                            vo.setProfilePath(credits.get("profile_path").toString());
                        }
                        creditList.add(vo);
                    }
                }
                /*감독 목록*/
            } else if (kind.equals("crew")) {
                for (int i = 0 ; i < list.size() ; i++) {
                    JSONObject credits = (JSONObject)list.get(i);
                    CreditsDto vo = new CreditsDto();
                    if (credits.get("known_for_department").equals("Directing")) {
                        vo.setName(credits.get("name").toString());
                        if(credits.get("profile_path") == null) {
                            vo.setProfilePath("");
                        } else {
                            vo.setProfilePath(credits.get("profile_path").toString());
                        }
                        creditList.add(vo);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return creditList;
    }


}
