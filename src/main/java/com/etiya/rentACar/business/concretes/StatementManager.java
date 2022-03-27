package com.etiya.rentACar.business.concretes;


import com.etiya.rentACar.business.abstracts.StatementService;
import com.etiya.rentACar.business.requests.statementRequests.CreateStatementRequest;
import com.etiya.rentACar.business.responses.maintenanceResponses.ListMaintenanceDto;
import com.etiya.rentACar.business.responses.statementResponses.ListStatementDto;
import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
import com.etiya.rentACar.core.utilities.results.DataResult;
import com.etiya.rentACar.core.utilities.results.Result;
import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
import com.etiya.rentACar.core.utilities.results.SuccessResult;
import com.etiya.rentACar.dataAccess.abstracts.StatementDao;
import com.etiya.rentACar.entities.Maintenance;
import com.etiya.rentACar.entities.Statement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatementManager implements StatementService {
    private StatementDao statementDao;
    private ModelMapperService modelMapperService;

    public StatementManager(StatementDao statementDao,ModelMapperService modelMapperService) {
        this.statementDao = statementDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateStatementRequest createStatementRequest) {
        Statement statement = this.modelMapperService.forRequest().map(createStatementRequest, Statement.class);
        this.statementDao.save(statement);
        return new SuccessResult("bakım için durum bilgisi başarıyla eklendi");
    }

}
