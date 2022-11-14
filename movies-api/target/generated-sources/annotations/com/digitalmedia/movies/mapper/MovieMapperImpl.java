package com.digitalmedia.movies.mapper;

import com.digitalmedia.movies.model.Movie;
import com.digitalmedia.movies.model.dto.CreateMovieRequest;
import com.digitalmedia.movies.model.dto.MovieDto;
import com.digitalmedia.movies.model.dto.UpdateMovieRequest;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-14T08:48:28-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.12 (Amazon.com Inc.)"
)
@Component
public class MovieMapperImpl extends MovieMapper {

    @Override
    public Movie toMovie(CreateMovieRequest createMovieRequest) {
        if ( createMovieRequest == null ) {
            return null;
        }

        Movie movie = new Movie();

        movie.setImdbId( createMovieRequest.getImdbId() );
        movie.setTitle( createMovieRequest.getTitle() );
        movie.setDirector( createMovieRequest.getDirector() );
        movie.setYear( createMovieRequest.getYear() );
        movie.setPoster( createMovieRequest.getPoster() );

        return movie;
    }

    @Override
    public void updateMovieFromDto(UpdateMovieRequest updateMovieRequest, Movie movie) {
        if ( updateMovieRequest == null ) {
            return;
        }

        if ( updateMovieRequest.getTitle() != null ) {
            movie.setTitle( updateMovieRequest.getTitle() );
        }
        if ( updateMovieRequest.getDirector() != null ) {
            movie.setDirector( updateMovieRequest.getDirector() );
        }
        if ( updateMovieRequest.getYear() != null ) {
            movie.setYear( updateMovieRequest.getYear() );
        }
        if ( updateMovieRequest.getPoster() != null ) {
            movie.setPoster( updateMovieRequest.getPoster() );
        }
    }

    @Override
    public MovieDto toMovieDto(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieDto movieDto = new MovieDto();

        movieDto.setImdbId( movie.getImdbId() );
        movieDto.setTitle( movie.getTitle() );
        movieDto.setDirector( movie.getDirector() );
        movieDto.setYear( movie.getYear() );
        movieDto.setPoster( movie.getPoster() );
        movieDto.setComments( commentListToCommentDtoList( movie.getComments() ) );

        return movieDto;
    }

    @Override
    public MovieDto.CommentDto toMovieDtoCommentDto(Movie.Comment comment) {
        if ( comment == null ) {
            return null;
        }

        MovieDto.CommentDto commentDto = new MovieDto.CommentDto();

        commentDto.setUsername( comment.getUsername() );
        commentDto.setText( comment.getText() );
        commentDto.setTimestamp( comment.getTimestamp() );

        return commentDto;
    }

    protected List<MovieDto.CommentDto> commentListToCommentDtoList(List<Movie.Comment> list) {
        if ( list == null ) {
            return null;
        }

        List<MovieDto.CommentDto> list1 = new ArrayList<MovieDto.CommentDto>( list.size() );
        for ( Movie.Comment comment : list ) {
            list1.add( toMovieDtoCommentDto( comment ) );
        }

        return list1;
    }
}
