package uk.gov.di.ipv.cri.experian.kbv.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.gov.di.ipv.cri.experian.kbv.api.domain.QuestionAnswerRequest;
import uk.gov.di.ipv.cri.experian.kbv.api.domain.QuestionRequest;
import uk.gov.di.ipv.cri.experian.kbv.api.domain.QuestionsResponse;
import uk.gov.di.ipv.cri.experian.kbv.api.gateway.KBVGateway;

public class KBVService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KBVService.class);
    public static final String ERROR_OCCURRED_ATTEMPTING_TO_INVOKE_EXPERIAN_API = "Error occurred when attempting to invoke experian api";
    private final KBVGateway kbvGateway;

    public KBVService(KBVGateway kbvGateway) {
        this.kbvGateway = kbvGateway;
    }

    public QuestionsResponse getQuestions(QuestionRequest questionRequest) {
        return this.kbvGateway.getQuestions(questionRequest);
    }

    public QuestionsResponse submitAnswers(QuestionAnswerRequest answers) {
        try {
            return kbvGateway.submitAnswers(answers);
        } catch (InterruptedException ie) {
            LOGGER.error(ERROR_OCCURRED_ATTEMPTING_TO_INVOKE_EXPERIAN_API, ie);
            Thread.currentThread().interrupt();
            return null;
        } catch (Exception e) {
            LOGGER.error(ERROR_OCCURRED_ATTEMPTING_TO_INVOKE_EXPERIAN_API, e);
            return null;
        }
    }
}
