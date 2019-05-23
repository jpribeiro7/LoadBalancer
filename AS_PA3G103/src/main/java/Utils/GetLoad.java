/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.google.gson.annotations.Expose;

/**
 *
 * @author Pedro
 */
public class GetLoad {
    @Expose
    private String type;
    @Expose
    private int load;

    public GetLoad(String type, int load) {
        this.type = type;
        this.load = load;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }
    
}
