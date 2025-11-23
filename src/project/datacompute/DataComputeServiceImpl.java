package project.datacompute;

import io.grpc.stub.StreamObserver;
import project.datacompute.DataComputeApi.ReadInputRequest;
import project.datacompute.DataComputeApi.ReadInputResponse;
import project.datacompute.DataComputeApi.WriteOutputRequest;
import project.datacompute.DataComputeApi.WriteOutputResponse;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataComputeServiceImpl extends DataComputeServiceGrpc.DataComputeServiceImplBase {

    @Override
    public void readInput(ReadInputRequest request,
                          StreamObserver<ReadInputResponse> responseObserver) {

        String path = request.getInputFile();

        try {
            List<Integer> nums = new ArrayList<>();

            for (String line : Files.readAllLines(Paths.get(path))) {
                if (!line.isBlank()) {
                    nums.add(Integer.parseInt(line.trim()));
                }
            }

            ReadInputResponse response = ReadInputResponse.newBuilder()
                    .addAllNumbers(nums)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {

            ReadInputResponse response = ReadInputResponse.newBuilder()
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void writeOutput(WriteOutputRequest request,
                            StreamObserver<WriteOutputResponse> responseObserver) {

        try {
            List<Integer> nums = request.getNumbersList();
            String path = request.getOutputFile();

            Files.write(Paths.get(path),
                    nums.stream().map(String::valueOf).toList());

            WriteOutputResponse response = WriteOutputResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("File written successfully")
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {

            WriteOutputResponse response = WriteOutputResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage(e.toString())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
