package com.magnakod.emulator.client_token;

import com.magnakod.emulator.SpotifyBase;
import com.magnakod.emulator.SpotifyConstants;
import com.magnakod.emulator.base.request.BaseRequest;
import com.magnakod.emulator.builder.SpotifyHeaders;
import com.magnakod.emulator.proto.generated.clientToken.SpClientWithTokenResponse;
import com.magnakod.emulator.proto.objects.client_token.ClientTokenFinal;
import com.magnakod.emulator.proto.objects.client_token.ClientTokenFirst;
import com.magnakod.emulator.proto.objects.client_token.SpotifyClientTokenProto;
import com.magnakod.emulator.session.SpotifySession;
import com.magnakod.emulator.utils.HashCash;
import okhttp3.Headers;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;

public class SpotifyClientToken extends SpotifyBase {
    private final SpotifyConstants spConstant;
    private String hashcash_token;
    private final SpotifyHeaders headers;
    private final BaseRequest request;
    private final Logger logger = LoggerFactory.getLogger(SpotifyClientToken.class);
    private final SpotifySession session;

    public SpotifyClientToken(SpotifySession session,SpotifyHeaders headers, SpotifyConstants constants, BaseRequest request) {
        super(headers, constants, request);
        this.spConstant = constants;
        this.headers = headers;
        this.request = request;
        this.session = session;
    }

    public Response clientTokenWithoutToken() throws Exception {
        String url = "https://clienttoken.spotify.com/v1/clienttoken";
        ClientTokenFirst clientTokenFirst = new ClientTokenFirst(headers);

        HashMap<String,String> header = new HashMap<>();
        header.put("Accept","application/x-protobuf");
        header.put("User-Agent",session.getVersion().getUserAgent());

        byte[] decoded_post_data = clientTokenFirst.create();

        this.request.setContent_type("application/x-protobuf");
        Headers headers = Headers.of(header);
        Response response = this.request.post_request(url,decoded_post_data,headers);

        return response;
    }

    public Response clientTokenWithToken(String clientToken) throws Exception {
        String url = "https://clienttoken.spotify.com/v1/clienttoken";
        ClientTokenFinal clientTokenFinal = new ClientTokenFinal(hashcash_token,clientToken);

        HashMap<String,String> header = new HashMap<>();
        header.put("Accept","application/x-protobuf");
        header.put("User-Agent",session.getVersion().getUserAgent());

        byte[] decoded_post_data = clientTokenFinal.create();

        this.request.setContent_type("application/x-protobuf");
        Headers headers = Headers.of(header);
        Response response = this.request.post_request(url,decoded_post_data,headers);

        return response;
    }


    public String get_full_token() throws Exception{
        byte[] client_token_response = Objects.requireNonNull(clientTokenWithoutToken().body()).bytes();
        SpotifyClientTokenProto spotifyClientTokenProto = new SpotifyClientTokenProto();
        HashMap<String, Object> values = (HashMap<String, Object>) spotifyClientTokenProto.parse(client_token_response);

        HashCash hashCash = new HashCash();
        hashcash_token = hashCash.solveChallenge((String) values.get("hashcash_token"), (Integer) values.get("hashcash_length"));

        logger.info("{} : hashcash challenge - solved: {}", session.getEmailAddress() ,hashcash_token);
        byte[] client_token_verificated = Objects.requireNonNull(clientTokenWithToken((String) values.get("client_token")).body()).bytes();

        headers.setClientToken(SpClientWithTokenResponse.SPClientWithTokenResponse.parseFrom(client_token_verificated).getClientToken().getClientToken());
        return headers.getClientToken();
    }
}