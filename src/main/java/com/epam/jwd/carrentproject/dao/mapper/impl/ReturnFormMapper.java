package com.epam.jwd.carrentproject.dao.mapper.impl;

import com.epam.jwd.carrentproject.dao.mapper.Mapper;
import com.epam.jwd.carrentproject.entity.ReturnForm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.jwd.carrentproject.dao.ColumnName.*;

public class ReturnFormMapper implements Mapper<ReturnForm> {

    private static final ReturnFormMapper instance = new ReturnFormMapper();

    private ReturnFormMapper() {

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static ReturnFormMapper getInstance() {
        return instance;
    }

    @Override
    public List<ReturnForm> retrieve(ResultSet resultSet) throws SQLException {
        List<ReturnForm> returnFormList = new ArrayList<>();

        while(resultSet.next()) {
            ReturnForm returnForm = new ReturnForm.ReturnFormBuilder()
                    .withReturnFormId(resultSet.getInt(RETURN_ID))
                    .withOrderId(resultSet.getInt(ORDER_ID))
                    .withDateOfReturn(resultSet.getDate(DATE_OF_RETURN).toLocalDate())
                    .withDamageDescription(resultSet.getString(DAMAGE_DESCRIPTION))
                    .withBillValue(resultSet.getBigDecimal(BILL_VALUE))
                    .build();

            returnFormList.add(returnForm);

        }

        return returnFormList;
    }
}
