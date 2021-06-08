package com.example.RentaBikeTestTwo.serviceImpl;

import com.example.RentaBikeTestTwo.domain.Bike;
import com.example.RentaBikeTestTwo.domain.Customer;
import com.example.RentaBikeTestTwo.domain.Rental;
import com.example.RentaBikeTestTwo.exceptions.*;
import com.example.RentaBikeTestTwo.payload.request.*;
import com.example.RentaBikeTestTwo.payload.response.RentalResponse;
import com.example.RentaBikeTestTwo.repository.BikeRepository;
import com.example.RentaBikeTestTwo.repository.CustomerRepository;
import com.example.RentaBikeTestTwo.repository.RentalRepository;
import com.example.RentaBikeTestTwo.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;


@Service
public class RentalServiceImpl implements RentalService {

    private List<Bike> bikes = new ArrayList<>();
    private double rentalPrice;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    public void setRentalRepository(RentalRepository rentalRepository){
        this.rentalRepository = rentalRepository;
    }


    public Collection<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    public ResponseEntity<?> getRentalInfoById(long id) {

        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if(optionalRental.isEmpty()) {
            throw new RentalNotFoundException(id);
        }
        Rental rental = optionalRental.get();
        RentalResponse rentalResponse = getRentalResponseObject(rental);
        if (rental.getCustomer() == null){
            return ResponseEntity.status(404).body("No customer found, please add customer to rental.");
        }

        return ResponseEntity.ok(rentalResponse);
    }


    public ResponseEntity<?> createRental(RentalRequest rentalRequest){
        Rental rental = new Rental();
        rentalRepository.save(rental);
        return new ResponseEntity<>("Rental " + rental.getId() + " has been created", HttpStatus.OK );
    }


    private RentalResponse getRentalResponseObject(Rental rental){

        RentalResponse rentalResponse = new RentalResponse();
        rentalResponse.setCustomer(rental.getCustomer());
        rentalResponse.setBikes(bikes);
        rentalResponse.setId(rental.getId());

        double totalPrice = 0;
        for (Bike bike : bikes) { //totaalprijs
            totalPrice = bike.getRentalPrice() + totalPrice;
        }
        rentalResponse.setTotalPrice(totalPrice);
        return rentalResponse;
    }

    public boolean doesBikeExist(String bikeNumber){

        Optional<Bike> optionalBike = bikeRepository.findByBikeNumber(bikeNumber);
        if (optionalBike.isEmpty()){
            throw new BikeNotFoundException(bikeNumber);
        }
        return true;
    }

    public boolean doesRentalExist(long id){
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isEmpty()){
            throw new RentalNotFoundException(id);
        }
        return true;
    }

    public boolean doesCustomerExist(long id){
        Optional<Customer> optionalCustomer = customerRepository.findCustomerById(id);
        if (optionalCustomer.isEmpty()){
            throw new CustomerNotFoundException(id);
        }
        return true;
    }

    public ResponseEntity<?> addBikeToRental(long id, AddBikeRequest addBikeRequest){

        doesBikeExist(addBikeRequest.getBikeNumber());
        doesRentalExist(id);

        Bike bike = bikeRepository.findByBikeNumber(addBikeRequest.getBikeNumber()).orElse(null);
        Rental rental = rentalRepository.findById(id).orElse(null);

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd-MM-yyyy")
                .toFormatter(Locale.ENGLISH);
        LocalDate startDate = LocalDate.parse(addBikeRequest.getBeginDate(), formatter);
        LocalDate returnDate = LocalDate.parse(addBikeRequest.getEndDate(), formatter);
        Period rentalPeriod = Period.between(startDate, returnDate);
        long rentalDays = rentalPeriod.getDays();
        bike.setRentalDays(rentalDays);

        if (bike.getRentalDays() <= 0){
          throw new IncorrectDateException();
        }

        calculatePrice(bike);
        bikes = rental.getBikes();

        if (!bikes.contains(bike)){
            bikes.add(bike);
            rental.setBikes(bikes);
            bike.setReturnDate(returnDate);
            bike.setRentalPrice(rentalPrice);
            bike.setRentalDays(rentalDays);
            bike.setRented(true);
            rentalRepository.save(rental);
            }

        else if (bikes.contains(bike)){
          throw new DuplicateBikeException(bike);
            }

        Formatter priceFormat = new Formatter();
        priceFormat.format(" %.2f ", rentalPrice);

        if (rentalDays == 1) {
           return ResponseEntity.ok("Bike " + bike.getBikeNumber() + " added for " + rentalDays + " day, at the cost of: €" + priceFormat);
        } else return
            ResponseEntity.ok("Bike " + bike.getBikeNumber() + " added for " + rentalDays + " days, at the cost of: €" + priceFormat);
    }


    public Bike findBikeByBikeNumberInList(List<Bike> bikes, String bikeNumber) {
        for (Bike bike : bikes){
            if (bike.getBikeNumber().equals(bikeNumber)){
                return bike;
            }
        }
        throw new BikeNotFoundException(bikeNumber);
    }


    public ResponseEntity<?> payBike(long id, PayBikeRequest payBikeRequest){

        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isEmpty()){
            throw new RentalNotFoundException(id);
        }

        Rental rental = optionalRental.get();

        Optional<Bike> optionalBike = bikeRepository.findByBikeNumber(payBikeRequest.getBikeNumber());
            if (optionalBike.isEmpty()){
                throw new BikeNotFoundException(payBikeRequest.getBikeNumber());
            }

        Bike bike = optionalBike.get();
        LocalDate localDate = LocalDate.now();
        bike.setReturnDate(localDate);
        bike.setRentalDays(0);
        rentalRepository.save(rental);

        return ResponseEntity.ok("Bike " + payBikeRequest.getBikeNumber() + " is paid.");
    }

    public ResponseEntity<?> returnBike(long id, ReturnBikeRequest returnBikeRequest) {


        if(bikes.isEmpty()){
            return ResponseEntity.status(404).body("Bike list is empty.");
        }

        doesRentalExist(id);
         // nog even uitzoeken of ik null weg kan doen.
        Rental rental = rentalRepository.findById(id).orElse(null);

        assert rental != null;
        bikes = rental.getBikes();
        Bike bike = findBikeByBikeNumberInList(bikes, returnBikeRequest.getBikeNumber());

        if (!bikes.contains(bike)){
            return ResponseEntity.status(404).body("This bike is not in this list.");
        }

        LocalDate localDate = LocalDate.now();
        long overdue = DAYS.between(bike.getReturnDate(), localDate);

        if (!bike.getReturnDate().equals(localDate) && bikes.contains(bike)) {
            bike.setRentalDays(overdue);
            if (overdue <= -1){
                return ResponseEntity.status(400).body("Bike is too early, further action required.");
            }
            else
                return ResponseEntity.status(400).body("Bike nr: " + bike.getBikeNumber()
                        + " is " + overdue + " days overdue."
                        + " You still need to pay: €" + calculatePrice(bike));
        }
        else if (bike.getReturnDate().equals(localDate) && bikes.contains(bike)) {
                    bike.setRentalPrice(0);
                    bike.setRented(false);
                    bike.setReturnDate(null);
                    bikes.remove(bike);
                    rental.setBikes(bikes);
                    rentalRepository.save(rental);
                    return ResponseEntity.ok("Bike has returned safely.");
                }
        return ResponseEntity.status(400).body("Error");
    }

    public double calculatePrice(Bike bike){

       long rentalDays = bike.getRentalDays();
        if (rentalDays == 1){
            rentalPrice = bike.getBasePrice() * 1.25;
        }
        if (rentalDays == 2){
            rentalPrice = bike.getBasePrice() * 2.25;
        }
        if (bike.isElectric() && rentalDays >= 3){
            rentalPrice = bike.getBasePrice() + (rentalDays * 15);
        }
        else if (!bike.isElectric() && rentalDays >= 3){
            rentalPrice = bike.getBasePrice() + (rentalDays * 7.5);
        }
        return rentalPrice;
    }


    public ResponseEntity<?> addCustomerToRental(long id, AddCustomerRequest addCustomerRequest){

        Optional<Customer> optionalCustomer = customerRepository.findCustomerById(addCustomerRequest.getId());
            if (optionalCustomer.isEmpty()){
                throw new CustomerNotFoundException(addCustomerRequest.getId());
            }
        doesRentalExist(id);

        Rental rental = rentalRepository.findById(id).orElse(null);

        assert rental != null;
        if (rental.getCustomer() == null){
                rental.setCustomer(optionalCustomer.get());
                rentalRepository.save(rental);
                return ResponseEntity.ok("Customer " + optionalCustomer.get().getId() + " "
                        + optionalCustomer.get().getFirstName() + " " + optionalCustomer.get().getLastName()
                        + " added to rental " + rental.getId());
            }
            if (rental.getCustomer().getId() == addCustomerRequest.getId()){
                return ResponseEntity.status(400).body("Customer " + rental.getCustomer().getFirstName() + " " +
                        rental.getCustomer().getLastName() + " is already added to this rental.");
            }
            if (rental.getCustomer().getId() != addCustomerRequest.getId()){
                return ResponseEntity.status(400).body("This rental is already assigned to " +
                        rental.getCustomer().getFirstName() + " " + rental.getCustomer().getLastName() +
                        ". Try using a different rental id.");
            }

        return ResponseEntity.ok("error");


    }



}
