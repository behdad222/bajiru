package app.bajiru.ir.Object.Response;

import app.bajiru.ir.Object.Model.Customer;

public class CustomersResponse {
    private Customer[] customer;

    public Customer[] getCustomer() {
        return customer;
    }

    public void setCustomer(Customer[] customer) {
        this.customer = customer;
    }
}
