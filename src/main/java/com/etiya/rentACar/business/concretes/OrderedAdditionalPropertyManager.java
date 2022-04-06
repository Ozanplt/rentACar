//package com.etiya.rentACar.business.concretes;
//
//import com.etiya.rentACar.business.abstracts.OrderedAdditionalPropertyService;
//import com.etiya.rentACar.business.requests.orderedAdditionalPropertyRequests.CreateOrderedAdditionalPropertyRequest;
//import com.etiya.rentACar.business.responses.additionalPropertyResponses.ListAdditionalPropertyDto;
//import com.etiya.rentACar.business.responses.orderedAdditionalPropertyResponses.ListOrderedAdditionalPropertyDto;
//import com.etiya.rentACar.core.utilities.mapping.ModelMapperService;
//import com.etiya.rentACar.core.utilities.results.DataResult;
//import com.etiya.rentACar.core.utilities.results.Result;
//import com.etiya.rentACar.core.utilities.results.SuccessDataResult;
//import com.etiya.rentACar.core.utilities.results.SuccessResult;
//import com.etiya.rentACar.dataAccess.abstracts.OrderedAdditionalPropertyDao;
//import com.etiya.rentACar.entities.AdditionalProperty;
//import com.etiya.rentACar.entities.OrderedAdditionalProperty;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class OrderedAdditionalPropertyManager implements OrderedAdditionalPropertyService {
//
//    private OrderedAdditionalPropertyDao orderedAdditionalPropertyDao;
//    private ModelMapperService modelMapperService;
//
//    public OrderedAdditionalPropertyManager(OrderedAdditionalPropertyDao orderedAdditionalPropertyDao, ModelMapperService modelMapperService) {
//        this.orderedAdditionalPropertyDao = orderedAdditionalPropertyDao;
//        this.modelMapperService = modelMapperService;
//    }
//
//    @Override
//    public Result add(CreateOrderedAdditionalPropertyRequest createOrderedAdditionalPropertyRequest) {
//        OrderedAdditionalProperty orderedAdditionalProperty = this.modelMapperService.forRequest().map(createOrderedAdditionalPropertyRequest,OrderedAdditionalProperty.class);
//        orderedAdditionalPropertyDao.save(orderedAdditionalProperty);
//        return new SuccessResult("Order added to payment");
//    }
//
//    @Override
//    public DataResult<List<ListOrderedAdditionalPropertyDto>> getAll() {
//        List<OrderedAdditionalProperty> orderedList = this.orderedAdditionalPropertyDao.findAll();
//        List<ListOrderedAdditionalPropertyDto> response = orderedList.stream()
//                .map(order -> this.modelMapperService.forDto().map(order, ListOrderedAdditionalPropertyDto.class))
//                .collect(Collectors.toList());
//        return new SuccessDataResult<List<ListOrderedAdditionalPropertyDto>>(response);
//    }
//
//    public ListOrderedAdditionalPropertyDto getById(int id){
//        OrderedAdditionalProperty response = this.orderedAdditionalPropertyDao.getById(id);
//        ListOrderedAdditionalPropertyDto result = this.modelMapperService.forDto().map(response, ListOrderedAdditionalPropertyDto.class);
//        return result;
//    }
//
//    public List<ListOrderedAdditionalPropertyDto> getAllIdById(int id){
//        List<OrderedAdditionalProperty> response = this.orderedAdditionalPropertyDao.getAllIdById(id);
//        List<ListOrderedAdditionalPropertyDto> result = response.stream()
//                .map(order -> this.modelMapperService.forDto().map(order, ListOrderedAdditionalPropertyDto.class))
//                .collect(Collectors.toList());
//
//        return result;
//    }
//}
