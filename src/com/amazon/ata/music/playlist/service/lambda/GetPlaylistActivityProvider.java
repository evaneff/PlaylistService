package com.amazon.ata.music.playlist.service.lambda;

import com.amazon.ata.music.playlist.service.dependency.DaggerServiceComponent;
import com.amazon.ata.music.playlist.service.dependency.ServiceComponent;
import com.amazon.ata.music.playlist.service.models.requests.GetPlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.GetPlaylistResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetPlaylistActivityProvider implements RequestHandler<GetPlaylistRequest, GetPlaylistResult> {


    public GetPlaylistActivityProvider() {

    }

    @Override
    public GetPlaylistResult handleRequest(final GetPlaylistRequest getPlaylistRequest, Context context) {
        return daggerServiceComponent().provideGetPlaylistActivity().handleRequest(getPlaylistRequest, context);
    }

//    private App getApp() {
//        if (app == null) {
//            app = new App();
//        }
//        return app;
//    }
    public ServiceComponent daggerServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }



}
