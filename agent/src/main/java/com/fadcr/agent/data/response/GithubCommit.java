package com.fadcr.agent.data.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GithubCommit {


        private String message;

        public String getDate() {
                return committer.date;
        }


        private static class committer{
                private static String date;
        }


}
