package app.bajiru.ir.object.Response;

import app.bajiru.ir.object.Model.Customer;

public class CustomersResponse {
    private Customer[] customer;

    public Customer[] getCustomer() {
        return customer;
    }

    public void setCustomer(Customer[] customer) {
        this.customer = customer;
    }
}
