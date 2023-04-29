package com.example.pass.service.pass;

import com.example.pass.controller.admin.BulkPassRequest;
import com.example.pass.repository.packaze.PackageEntity;
import com.example.pass.repository.packaze.PackageRepository;
import com.example.pass.repository.pass.BulkPassEntity;
import com.example.pass.repository.pass.BulkPassRepository;
import com.example.pass.repository.pass.BulkPassStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BulkPassService {

    private final BulkPassRepository bulkPassRepository;
    private final PackageRepository packageRepository;

    public List<BulkPass> getAllBulkPasses(){
        // startedAt을 역순으로 모든 BulkPass를 조회.
        List<BulkPassEntity> bulkPassEntities = bulkPassRepository.findAllOrderByStartedAtDesc();
        return BulkPassModelMapper.INSTANCE.map(bulkPassEntities);
    }

    public void addBulkPass(BulkPassRequest bulkPassRequest){
        // bulkPassRequest를 기반으로 passEntity를 생성하여 DB에 저장.
        PackageEntity packageEntity = packageRepository.findById(bulkPassRequest.getPackageSeq()).orElseThrow();

        BulkPassEntity bulkPassEntity = BulkPassModelMapper.INSTANCE.map(bulkPassRequest);
        bulkPassEntity.setStatus(BulkPassStatus.READY);
        bulkPassEntity.setCount(packageEntity.getCount());
        bulkPassEntity.setEndedAt(packageEntity.getPeriod());
        bulkPassRepository.save(bulkPassEntity);
    }
}
