package com.ib.cat.service.media;

import com.ib.cat.dto.media.ContentsDto;
import com.ib.cat.dto.media.CreditsDto;
import com.ib.cat.dto.media.GenresDto;
import com.ib.cat.dto.media.OttDto;
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

    /*  contents 상세페이지    */
    public ContentsDto getSpecificContent(String type, int contentsNum) {
        ContentsDto sContent = null;
        List<String> genreList = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "0001-01-01";//기본값
        try {
            sContent = new ContentsDto();

            URL url = new URL(API_URL+type+"/"+contentsNum+"?api_key="+KEY+"&language=ko");
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject contents = (JSONObject)jsonParser.parse(result);

            ContentsDto vo = new ContentsDto();
            vo.setContentsNum(Integer.parseInt(String.valueOf(contents.get("id"))));
            vo.setContentsType(type);
            System.out.println(type);

            if (contents.get("overview").equals("")) {
                vo.setOverview("등록 전입니다.");
            } else {
                vo.setOverview(contents.get("overview").toString());
            }

            //컨텐츠 타입에 따라 파싱 방법 다르게 설정
            if(type.equals("movie")) {
                //시리즈일 경우 release_date를 key로 데이터 파싱
                if(contents.get("release_date") == null || contents.get("release_date").equals("")) {
                    vo.setReleaseDate(dateFormat.parse(date));
                } else { //시리즈 아님
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
                vo.setPosterPath("default");
            } else {
                vo.setPosterPath("https://image.tmdb.org/t/p/w300"+contents.get("poster_path").toString());
            }
            
            if (type.equals("movie")) {
                String runtime = String.valueOf(contents.get("runtime"));
                int hour = Integer.parseInt(runtime) / 60;
                int minute = Integer.parseInt(runtime) % 60;
//                vo.setHour(hour);
                vo.setMinute(minute);
                vo.setRuntime(runtime);
            } else { /*  tv 상영 정보  */
                int hour = Integer.parseInt(contents.get("number_of_seasons").toString()); //hour:season
                int minute = Integer.parseInt(contents.get("number_of_episodes").toString()); //minute:episode
                vo.setHour(hour);
                vo.setMinute(minute);
//                List<Long> runtime = (List<Long>)contents.get("episode_run_time"); //[숫자]로 나옴
//                if (runtime.isEmpty()) {
//                    vo.setRuntime("default");
//                } else {
//                    vo.setRuntime(runtime.get(0).toString());
//                }

            }
            /*  장르  */
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
            }
            vo.setLs(tmpls);
//            System.out.println(vo.getLs().size());
//            for(GenresDto g : vo.getLs())
//                System.out.println("id: "+g.getGenreId()+"\n"
//                        +"name : "+g.getGenreName());
            sContent = vo;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return sContent;
    }

    //List 가져오기 (i page)
    public List<ContentsDto> getInfoPageList(String type, String sortBy, int page, String platform) {
        //int pages = getPages(type, sortBy);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "0001-01-01";

        List<ContentsDto> infoList = null;
        List<Integer> genreList = null;

        try {
            infoList = new ArrayList<ContentsDto>();
            URL url;
            if (platform.equals("") || platform.equals("none")) {
                url = new URL(API_URL+"discover/"+type+"?api_key="+KEY
                        +"&language=ko&sort_by="+sortBy+"&include_adult=false&page="+page);
                System.out.println("getInfoPageList - 실행된 api: "+API_URL+"/discover/"+type+"?api_key="+KEY
                        +"&language=ko&sort_by="+sortBy+"&include_adult=false&page="+page);
            } else {
                int providerCode;
                if (platform.equals("Netflix")) {
                    providerCode=8;
                } else if (platform.equals("Disney Plus")) {
                    providerCode=337;
                } else if (platform.equals("Wavve")) {
                    providerCode=356;
                } else if (platform.equals("Watcha")) {
                    providerCode=97;
                } else {
                    providerCode=0;
                }
                url = new URL(API_URL+"discover/"+type+"?api_key="+KEY
                        +"&language=ko&sort_by="+sortBy+"&include_adult=false&page="+page+"&with_watch_providers="+providerCode+"&watch_region=KR");
                System.out.println("getInfoPageList - 실행된 api: "+API_URL+"/discover/"+type+"?api_key="+KEY
                        +"&language=ko&sort_by="+sortBy+"&include_adult=false&page="+page+"&with_watch_providers="+providerCode+"&watch_region=KR");
            }

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
                    vo.setPosterPath("default");
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

    /*  컨텐츠 리스트 전체 추출  */
    public List<ContentsDto> getInfoList(String type) {

        int pages = 500;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "0001-01-01";

        List<ContentsDto> infoList = null;
        List<Integer> genreList = null;

        try {
            infoList = new ArrayList<ContentsDto>();

            for (int i = 1; i <= pages ; i++) {
                URL url = new URL(API_URL+"discover/"+type+"?api_key="+KEY
                        +"&language=ko&include_adult=false&page="+i);

                BufferedReader bf;
                bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
                result = bf.readLine();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
                JSONArray list = (JSONArray)jsonObject.get("results");

                for (int j = 0 ; j < list.size() ; j++) {
                    genreList = new ArrayList<Integer>();
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

    public int getPages(String type, String sortBy) {
        int pages = 0;

        try {
            URL url = new URL(API_URL+"discover/"+type+"?api_key="+KEY
                    +"&language=ko&sort_by="+sortBy);
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();

            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            String pagesS = jsonObject.get("total_pages").toString();
            pages = Integer.parseInt(pagesS);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pages;
    }


    public List<String> getImages(String type, int id) {
        //이미지 주소 - String
        List<String> imageList = null;

        try {
            imageList = new ArrayList<String>();
            String apiURL = API_URL + type + "/" + id + "/images?api_key=" + KEY;
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
            String apiURL = API_URL + type + "/" + id + "/credits?api_key=" + KEY;
            URL url = new URL(apiURL);
            System.out.println(apiURL);

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
                            vo.setProfilePath("default");
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
                        System.out.println("=======getCredit========");
                        System.out.println("crew: " + credits.get("name"));
                        creditList.add(vo);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return creditList;
    }

    public List<ContentsDto> getMovieList(int type){
        String apiURL = null;
        List<ContentsDto> test = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = "0001-01-01";
        /*
         * case 1 = 현재 상영중인 영화
         * case 2 = 인기있는 영화
         * case 3 = 평점이 높은 영화
         * */

        switch(type){
            case 1: apiURL=API_URL+ "movie/now_playing"  + "?api_key=" + KEY
                    + "&language=ko-KR&region=KR"; break;
            case 2: apiURL=API_URL+ "movie/popular"  + "?api_key=" + KEY
                    + "&language=ko-KR&region=KR"; break;
            case 3: apiURL=API_URL+ "movie/top_rated"  + "?api_key=" + KEY
                    + "&language=ko-KR"; break;
        }

        try {
            URL url = new URL(apiURL);
            System.out.println("URL url = "+ url);
            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));


            String result = bf.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONArray list = (JSONArray) jsonObject.get("results");

            test = new ArrayList<ContentsDto>();
            for (int j = 0; j < list.size(); j++) {
                ContentsDto vo = new ContentsDto();
                JSONObject contents = (JSONObject) list.get(j);

                vo.setContentsNum(Integer.parseInt(contents.get("id").toString()));
                vo.setOverview(contents.get("overview").toString());
                vo.setTitle(contents.get("title").toString());
                vo.setVoteAverage(Float.parseFloat(String.valueOf(contents.get("vote_average"))));
                vo.setPosterPath(contents.get("poster_path").toString());
                if(contents.get("release_date") == null || contents.get("release_date").equals("")) {
                    vo.setReleaseDate(dateFormat.parse(date));
                } else {
                    Date releaseDate = dateFormat.parse((String)contents.get("release_date"));
                    vo.setReleaseDate(releaseDate);
                }

                test.add(vo);
            }
        }catch(Exception e) {

        }
        return test;
    }

    public List<OttDto> getOTT(String type, int contentsNum) { //List반환 - 여러 ott 있는 곳도 있음
        List<OttDto> getOtt = new ArrayList<>();

        try {
            URL url = new URL(API_URL + type + "/" + contentsNum + "/watch/providers?api_key=" + KEY);
            BufferedReader bf;
            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject contents = (JSONObject) jsonParser.parse(result);
            JSONObject results = (JSONObject) contents.get("results");
            JSONObject country = (JSONObject) results.get("KR");
            if (country == null || country.equals("")) { //한국에서 상영해주는 ott가 없는 경우
                getOtt = null;
            } else { //상영하는 경우
                /*  flatrate => 넷플릭스 등, rent => 네이버스토어 or 구글플레이스토어 등  */
                JSONArray ottDetail;
                if (country.get("flatrate") == null || country.get("flatrate").equals("")) {
                    ottDetail = (JSONArray) country.get("rent");
                } else {
                    ottDetail = (JSONArray) country.get("flatrate");
                }
                for (int i = 0 ; i < ottDetail.size() ; i++) {
                    JSONObject tmp = (JSONObject)ottDetail.get(i);
                    OttDto odo = new OttDto();
                    odo.setLogoPath(tmp.get("logo_path").toString());
                    odo.setProviderId(Integer.parseInt(tmp.get("provider_id").toString()));
                    odo.setProviderName(tmp.get("provider_name").toString());
                    if (tmp.get("provider_name").toString().equals("Netflix")) {
                        odo.setOttAdd("https://www.netflix.com/kr/");
                    } else if (tmp.get("provider_name").toString().equals("Disney Plus")) {
                        odo.setOttAdd("https://www.disneyplus.com/ko-kr/welcome/stream-now");
                    } else if (tmp.get("provider_name").toString().equals("Google Play Movies")){
                        odo.setOttAdd("https://play.google.com/store/movies?hl=en&gl=KR");
                    } else if (tmp.get("provider_name").toString().equals("wavve")){
                        odo.setOttAdd("https://www.wavve.com/");
                    } else {
                        odo.setOttAdd("");
                    }
                    getOtt.add(odo);
                }
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return getOtt;
    }
}
