package com.etiya.rentACar.business.abstracts;

import com.etiya.rentACar.business.requests.statementRequests.CreateStatementRequest;
import com.etiya.rentACar.business.responses.statementResponses.ListStatementDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;

import java.util.List;

public interface StatementService {

    Result add(CreateStatementRequest createStatementRequest);

}
