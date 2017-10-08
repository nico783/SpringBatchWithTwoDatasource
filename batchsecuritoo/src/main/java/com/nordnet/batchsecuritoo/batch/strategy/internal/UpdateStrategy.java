package com.nordnet.batchsecuritoo.batch.strategy.internal;

import com.nordnet.batchsecuritoo.batch.strategy.ActualizationStrategy;
import com.nordnet.batchsecuritoo.destination.entity.Customer;
import com.nordnet.batchsecuritoo.destination.entity.CustomerData;
import com.nordnet.batchsecuritoo.destination.entity.DataType;
import com.nordnet.batchsecuritoo.destination.service.CustomerService;
import com.nordnet.batchsecuritoo.source.entity.SecuritooCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UpdateStrategy implements ActualizationStrategy {

    @Autowired
    private CustomerService customerService;

    public static final String UNIVERSE_SECURITOO = "SOO";

    @Override
    public Customer getCustomer(SecuritooCustomer securitooCustomer) {

        Customer customer = customerService.findByIdClient(securitooCustomer.getCustomerId());

        // Cas d'un insert
        if (customer == null) {
            customer = new Customer();

            CustomerData customerDataId = new CustomerData(securitooCustomer.getId(), DataType.TECHNICAL_ID.getLabel(), UNIVERSE_SECURITOO);
            CustomerData customerDataProduct = new CustomerData(securitooCustomer.getProduct(), DataType.PRODUCT.getLabel(), UNIVERSE_SECURITOO);
            CustomerData customerDataProductCode = new CustomerData(securitooCustomer.getProductCode(), DataType.PRODUCT_CODE.getLabel(), UNIVERSE_SECURITOO);
            CustomerData customerDataRetailerId = new CustomerData(securitooCustomer.getRetailerId(), DataType.RETAILER_ID.getLabel(), UNIVERSE_SECURITOO);

            Set<CustomerData> customerDatas = new HashSet<>();
            customerDatas.add(customerDataId);
            customerDatas.add(customerDataProduct);
            customerDatas.add(customerDataProductCode);
            customerDatas.add(customerDataRetailerId);

            customer.setCustomerDatas(customerDatas);

            // Cas d'un update
        } else {
            for (CustomerData customerData : customer.getCustomerDatas()) {
                if(DataType.TECHNICAL_ID.getLabel().equals(customerData.getDataType())){
                    customerData.setReference(securitooCustomer.getId());
                }else if(DataType.RETAILER_ID.getLabel().equals(customerData.getDataType())){
                    customerData.setReference(securitooCustomer.getRetailerId());
                }else if(DataType.PRODUCT_CODE.getLabel().equals(customerData.getDataType())) {
                    customerData.setReference(securitooCustomer.getProductCode());
                }else if(DataType.PRODUCT.getLabel().equals(customerData.getDataType())) {
                    customerData.setReference(securitooCustomer.getProduct());
                } else {
                    throw new RuntimeException("DataType "+ customerData.getDataType() + " inconnu.");
                }
            }
        }

        // Dans le cas d'un update comme d'un insert:
        customer.setFirstName(securitooCustomer.getFirstName());
        customer.setCity(securitooCustomer.getCity());
        customer.setCompanyName(securitooCustomer.getCompany());
        customer.setEmail(securitooCustomer.getEmail());
        customer.setIdClient(securitooCustomer.getCustomerId());
        customer.setLastName(securitooCustomer.getLastName());
        customer.setPhoneNumber(securitooCustomer.getPhoneNumber());
        customer.setGsm(securitooCustomer.getMobileNumber());
        customer.setEmail(securitooCustomer.getEmail());

        return customer;

    }
}
