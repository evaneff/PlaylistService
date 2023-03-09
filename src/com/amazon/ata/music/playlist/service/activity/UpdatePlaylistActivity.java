package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazon.ata.music.playlist.service.converters.ModelConverter;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeChangeException;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.music.playlist.service.exceptions.PlaylistNotFoundException;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.models.requests.UpdatePlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.UpdatePlaylistResult;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation of the UpdatePlaylistActivity for the MusicPlaylistService's UpdatePlaylist API.
 *
 * This API allows the customer to update their saved playlist's information.
 */
public class UpdatePlaylistActivity implements RequestHandler<UpdatePlaylistRequest, UpdatePlaylistResult> {
    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDao;

    /**
     * Instantiates a new UpdatePlaylistActivity object.
     *
//     * @param playlistDao PlaylistDao to access the playlist table.
     */
//    @Inject
//    public UpdatePlaylistActivity(PlaylistDao playlistDao) {
//        this.playlistDao = playlistDao;
//    }

    @Inject
    public UpdatePlaylistActivity() {
        playlistDao = new PlaylistDao(new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient(Regions.US_WEST_2)));
    }

    /**
     * This method handles the incoming request by retrieving the playlist, updating it,
     * and persisting the playlist.
     * <p>
     * It then returns the updated playlist.
     * <p>
     * If the playlist does not exist, this should throw a PlaylistNotFoundException.
     * <p>
     * If the provided playlist name or customer ID has invalid characters, throws an
     * InvalidAttributeValueException
     * <p>
     * If the request tries to update the customer ID,
     * this should throw an InvalidAttributeChangeException
     *
     * @param updatePlaylistRequest request object containing the playlist ID, playlist name, and customer ID
     *                              associated with it
     * @return updatePlaylistResult result object containing the API defined {@link PlaylistModel}
     */
    @Override
    public UpdatePlaylistResult handleRequest(final UpdatePlaylistRequest updatePlaylistRequest, Context context) {
        log.info("Received UpdatePlaylistRequest {}", updatePlaylistRequest);
        //validate playlist name from request

        //getPlaylist(id)
        Playlist playlist = playlistDao.getPlaylist(updatePlaylistRequest.getId());
        //if playlist does not exist, throw PlaylistNotFoundException
        if (playlist == null) {
            throw new PlaylistNotFoundException();
        }
        //if request tries to update the customer ID, throw InvalidAttributeChangeException
        if (!updatePlaylistRequest.getCustomerId().equals(playlist.getCustomerId())) {
            throw new InvalidAttributeChangeException();
        }
        Pattern pattern = Pattern.compile("[\"'/]");
        Matcher matcher1 = pattern.matcher(updatePlaylistRequest.getName());
        Matcher matcher2 = pattern.matcher(updatePlaylistRequest.getCustomerId());
        //if playlist has name or customer ID with invalid characters, throw InvalidAttributeValueException
        if (matcher1.find() || matcher2.find()) {
            throw new InvalidAttributeValueException();
        }
        //update playlist
        playlist.setName(updatePlaylistRequest.getName());
        playlist.setId(updatePlaylistRequest.getId());
        //save playlist
        playlistDao.savePlaylist(playlist);
        //create PlaylistModel
        PlaylistModel playlistModel = new ModelConverter().toPlaylistModel(playlist);
            //PlaylistModel playlistModel = new PlaylistModel();
        //set PlaylistModel with saved playlist data
//        playlistModel.setName(playlist.getName());
//        playlistModel.setId(playlist.getId());
//        playlistModel.setCustomerId(playlist.getCustomerId());
        //create UpdatePlaylistResponse
        //Set PlaylistModel field in UpdatePlaylist Response

        return UpdatePlaylistResult.builder()
                .withPlaylist(playlistModel)
                .build();
    }
}
