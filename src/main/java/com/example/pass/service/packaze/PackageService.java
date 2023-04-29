package com.example.pass.service.packaze;

import com.example.pass.repository.packaze.PackageEntity;
import com.example.pass.repository.packaze.PackageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PackageService {
    private final PackageRepository packageRepository;

    public List<Package> getAllPackages() {
        // 패키지 이름으로 정렬하여 모든 패키지를 조회.
        List<PackageEntity> bulkPassEntities = packageRepository.findAllByOrderByPackageName();
        return PackageModelMapper.INSTANCE.map(bulkPassEntities);
    }
}
