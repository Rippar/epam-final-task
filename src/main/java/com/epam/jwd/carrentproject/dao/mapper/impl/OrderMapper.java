package com.epam.jwd.carrentproject.dao.mapper.impl;

import com.epam.jwd.carrentproject.dao.mapper.Mapper;
import com.epam.jwd.carrentproject.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.jwd.carrentproject.dao.ColumnName.*;

/**
 * The {@code OrderMapper} class uses singleton pattern to control the number of mapper objects created.
 *
 * @author Dmitry Murzo
 * @see Mapper
 */
public class OrderMapper implements Mapper<Order> {

    private static final OrderMapper INSTANCE = new OrderMapper();

    private OrderMapper() {

    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static OrderMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Order> retrieve(ResultSet resultSet) throws SQLException {
        List<Order> orders = new ArrayList<>();

        while (resultSet.next()) {
            Order order = new Order.OrderBuilder()
                    .withOrderId(resultSet.getInt(ORDER_ID))
                    .withUserId(resultSet.getInt(USER_ID))
                    .withCarId(resultSet.getInt(CAR_ID))
                    .withPickUpDate(resultSet.getDate(PICK_UP_DATE).toLocalDate())
                    .withDropUpDate(resultSet.getDate(DROP_OFF_DATE).toLocalDate())
                    .withStatus(resultSet.getString(STATUS))
                    .build();

            orders.add(order);

        }
        return orders;
    }
}
