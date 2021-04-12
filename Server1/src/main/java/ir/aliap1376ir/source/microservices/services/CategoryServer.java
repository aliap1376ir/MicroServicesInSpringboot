package ir.aliap1376ir.source.microservices.services;

import io.grpc.stub.StreamObserver;
import ir.aliap1376ir.source.microservices.models.db.Category;
import ir.aliap1376ir.source.microservices.models.db.CategoryRepository;
import ir.aliap1376ir.source.microservices.models.transfer.CategoryServiceGrpc;
import ir.aliap1376ir.source.microservices.models.transfer.Models;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GRpcService
public class CategoryServer extends CategoryServiceGrpc.CategoryServiceImplBase {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void findById(Models.RequestParam request, StreamObserver<Models.Category> responseObserver) {

        System.out.println(request.getId());

        Category categoryJson = categoryRepository.findById(request.getId());

        Models.Category categoryProto = Models.Category.newBuilder()
                .setId(categoryJson.getId())
                .setName(categoryJson.getName())
                .build();

        responseObserver.onNext(categoryProto);
        responseObserver.onCompleted();
    }
}
