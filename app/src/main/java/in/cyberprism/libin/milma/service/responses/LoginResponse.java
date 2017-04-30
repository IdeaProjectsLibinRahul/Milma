package in.cyberprism.libin.milma.service.responses;

import in.cyberprism.libin.milma.configurations.Constants;
import in.cyberprism.libin.milma.service.responses.base.BaseResponse;

/**
 * Created by libin on 28/04/17.
 */

public class LoginResponse extends BaseResponse {
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public class Response {
        private String name;
        private Constants.Type type;
        private int userId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Constants.Type getType() {
            return type;
        }

        public void setType(Constants.Type type) {
            this.type = type;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "name='" + name + '\'' +
                    ", type=" + type +
                    ", userId=" + userId +
                    '}';
        }
    }
}
