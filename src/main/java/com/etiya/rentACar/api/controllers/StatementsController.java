package com.etiya.rentACar.api.controllers;


import com.etiya.rentACar.business.abstracts.StatementService;
import com.etiya.rentACar.business.requests.statementRequests.CreateStatementRequest;
import com.etiya.rentACar.business.responses.statementResponses.ListStatementDto;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statements")
public class StatementsController {
    private StatementService statementService;


    public StatementsController(StatementService statementService) {
        this.statementService = statementService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody CreateStatementRequest createStatementRequest){
        return this.statementService.add(createStatementRequest);
    }
}
