package swiftaproject.swifta.utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class ResponseUtil {

    public final String SUCCESS_STATUS = "Successful";
    public final String FAILURE_STATUS = "Failed";

    public Response getResponse( String status, String message, Object data) {

        Response response = new Response();
        response.setStatus(status);
        response.setMessage(message);
        response.setRespBody(data);
        return response;
    }


    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {

        private String status;
        private String message;
        private Object respBody;


        @Override
        public String toString() {

            ObjectMapper mapper = new ObjectMapper();
            try{

                Response response = new Response();
                response.setStatus(status);
                response.setMessage(message);
                response.setRespBody(respBody);
                return mapper.writeValueAsString(response);
            }
            catch (Exception ignored){}
            return null;
        }
    }

}
