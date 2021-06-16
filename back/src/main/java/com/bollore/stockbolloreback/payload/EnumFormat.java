package com.bollore.stockbolloreback.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnumFormat {
    private String value;
    private String description;

    public EnumFormat(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
