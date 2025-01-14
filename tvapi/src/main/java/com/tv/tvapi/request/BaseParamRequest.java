package com.tv.tvapi.request;

import com.tv.tvapi.utilities.ValidationUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

@Data
@NoArgsConstructor
public class BaseParamRequest {

    private String sortBy = "create_date";

    private String direction = "DESC";

    private Integer page = 0;

    private Integer pageSize = 15;

    private Integer active = 1;

    private Integer status = 1;

    public BaseParamRequest(Map<String, String> paramsRequest) {
        String sortBy = paramsRequest.get("sortBy");
        String direction = paramsRequest.get("direction");
        String page = paramsRequest.get("page");
        String pageSize = paramsRequest.get("pageSize");
        String status = paramsRequest.get("status");
        String active = paramsRequest.get("active");
        if (!ValidationUtil.isNullOrBlank(sortBy))
            this.sortBy = sortBy.trim();
        if (!ValidationUtil.isNullOrBlank(direction))
            this.direction = direction.trim();
        if (ValidationUtil.isNumeric(page))
            this.page = Integer.valueOf(page);
        if (ValidationUtil.isNumeric(pageSize))
            this.pageSize = Integer.valueOf(pageSize);
        if (ValidationUtil.isNumeric(status))
            this.status = Integer.valueOf(status);
        if (ValidationUtil.isNumeric(active))
            this.active = Integer.valueOf(active);
    }

    public Pageable toPageRequest() {
        return PageRequest.of(this.page, this.pageSize, Sort.by(Sort.Direction.fromString(this.direction), this.sortBy));
    }
}
