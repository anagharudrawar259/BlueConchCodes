import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MovieService {
	
	/*
	addKeyValuePairsToMap(Movie movie, Integer rating) : Set<Map.Entry<Movie, Integer>> 

	  +getHigherRatedMovieNames(int rating) : List<String>

	  +getMovieNamesOfSpecificGenre(String genre) : List<String>

	  +getMovieNamesReleasedAfterSpecificDateAndRatingLesserThanThree(LocalDate releaseDate) : List<String>

	  +getSortedMovieListByReleaseDate() : List<Movie>

	  +getSortedMovieListByRating() : Map<Movie, Integer>*/
	
	HashMap<Movie, Integer> movies=new HashMap<>();
	
	public void addKeyValuePairsToMap(Movie movie, Integer rating){
		movies.put(movie,rating);
	}
	
	public List<String> getHigherRatedMovieNames(int rating){
		List<String> ratedMovies=new ArrayList<String>();
		for(Entry<Movie, Integer> movie:movies.entrySet()) {
			if(movie.getValue()==rating) {
				ratedMovies.add(movie.getKey().getMovieName());
			}
		}
		return ratedMovies;
	}
	
	public List<String> getMovieNamesOfSpecificGenre(String genre){
		List<String> genreMovies=new ArrayList<String>();
		for(Movie movie:movies.keySet()) {
			if(movie.getGenre().equals(genre)) {
				genreMovies.add(movie.getMovieName());
			}
		}
		return genreMovies;
	}
	
	public List<String> getMovieNamesReleasedAfterSpecificDateAndRatingLesserThanThree(LocalDate releaseDate){
		List<String> specificMovies=new ArrayList<String>();
		
		for(Entry<Movie, Integer>movie:movies.entrySet()) {
			if(movie.getKey().getReleaseDate().isAfter(releaseDate) && movie.getValue()<3)
				specificMovies.add(movie.getKey().getMovieName());
		}
		return specificMovies;
	}
	
	public List<Movie> getSortedMovieListByReleaseDate(){
		List<Movie> sortedMoviesList= new ArrayList<Movie>(movies.keySet());
		Collections.sort(sortedMoviesList);
		return sortedMoviesList;
	}
	
	public Map<Movie, Integer> getSortedMovieListByRating(){
		List<Map.Entry<Movie, Integer> > list =
	               new LinkedList<Map.Entry<Movie, Integer> >(movies.entrySet());
		
		 Collections.sort(list, new Comparator<Map.Entry<Movie, Integer> >() {
	            public int compare(Map.Entry<Movie, Integer> o1,
	                               Map.Entry<Movie, Integer> o2)
	            {
	                return (o1.getValue()).compareTo(o2.getValue());
	            }
	        });
		 
		 HashMap<Movie, Integer> sortedMovieMap = new LinkedHashMap<Movie, Integer>();
	        for (Map.Entry<Movie, Integer> aa : list) {
	            sortedMovieMap.put(aa.getKey(), aa.getValue());
	        }
	        
	     return sortedMovieMap;
	}
	
	public static void main(String[] args) {
		MovieService movieService=new MovieService();
		movieService.addKeyValuePairsToMap(new Movie(1, "Dangal", "sport", LocalDate.of(2016, 12, 21)), 4);
		movieService.addKeyValuePairsToMap(new Movie(2, "Simmba", "drama", LocalDate.of(2018, 12, 28)), 2);
		movieService.addKeyValuePairsToMap(new Movie(3, "DDLJ", "love", LocalDate.of(1995, 12, 28)), 5);
		
		System.out.println("getMovieNamesReleasedAfterSpecificDateAndRatingLesserThanThree: "+movieService.getMovieNamesReleasedAfterSpecificDateAndRatingLesserThanThree(LocalDate.of(2018, 12, 27)));
		
		System.out.println("getMovieNamesOfSpecificGenre : " + movieService.getMovieNamesOfSpecificGenre("sport"));
		
		System.out.println("getSortedMovieListByReleaseDate:" + movieService.getSortedMovieListByReleaseDate());
	
		System.out.println("getHigherRatedMovieNames: "+movieService.getHigherRatedMovieNames(4));
		
		System.out.println("getSortedMovieListByRating: "+movieService.getSortedMovieListByRating());
	}
}
