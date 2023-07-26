package com.amazon.ata.music.playlist.service.lambda;

import com.amazon.ata.music.playlist.service.dependency.DaggerServiceComponent;
import com.amazon.ata.music.playlist.service.dependency.ServiceComponent;
import com.amazon.ata.music.playlist.service.models.requests.GetPlaylistSongsRequest;
import com.amazon.ata.music.playlist.service.models.results.GetPlaylistSongsResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetPlaylistSongsActivityProvider implements RequestHandler<GetPlaylistSongsRequest, GetPlaylistSongsResult> {

    @Override
    public GetPlaylistSongsResult handleRequest(final GetPlaylistSongsRequest getPlaylistSongsRequest, Context context) {
        return daggerServiceComponent().provideGetPlaylistSongsActivity().handleRequest(getPlaylistSongsRequest, context);
    }

    public ServiceComponent daggerServiceComponent() {
    ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
