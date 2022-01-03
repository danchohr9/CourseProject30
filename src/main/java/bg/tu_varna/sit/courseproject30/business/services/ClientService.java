package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.data.entities.City;
import bg.tu_varna.sit.courseproject30.data.entities.Client;
import bg.tu_varna.sit.courseproject30.data.repositorities.CityRepository;
import bg.tu_varna.sit.courseproject30.data.repositorities.ClientRepository;
import bg.tu_varna.sit.courseproject30.presentation.models.ClientViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ClientService {
    private final ClientRepository clientRepository = ClientRepository.getInstance();
    private final CityRepository cityRepository = CityRepository.getInstance();

    public static ClientService getInstance() {
        return ClientService.ClientServiceHolder.INSTANCE;
    }

    private static class ClientServiceHolder {
        public static final ClientService INSTANCE = new ClientService();
    }

    public String registerClient(String name, String surname, String phone, String address, String egn, String city){
        if(!name.isBlank() && !surname.isBlank() && !phone.isBlank() && !address.isBlank() && !egn.isBlank() && !city.isBlank()){
            if(!validateEGN(egn)) return "Wrong EGN format.";
            if(!validateName(name) || !validateName(surname)) return "Wrong name format.";
            if(!validateName(city)) return "Wrong City format.";
            if(address.length()<5) return "Address is too short";
            if(!validatePhone(phone)) return "Wrong phone format.";
            City city1 = checkCity(city);
            Date today = new Date();
            Client client = new Client(name, surname, phone, address, egn, today, city1);
            clientRepository.save(client);
            return "Successfully created.";
        }
        return "Please fill all fields.";
    }

    public City checkCity(String cityStr){
        City city = cityRepository.getCityByName(cityStr);
        if(city!=null) return city;
        else{
            city = new City();
            city.setName(cityStr);
            cityRepository.save(city);
            city.setId(cityRepository.getCityByName(cityStr).getId());
            return city;
        }
    }

    public boolean validateEGN(String egn){
        if(egn.length()!=10) return false;
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(egn);
        if(matcher.find() && matcher.group().equals(egn)){
            return true;
        }else{
            return false;
        }
    }

    public boolean validateName(String name){
        Pattern pattern = Pattern.compile("[a-zA-Z]*");
        Matcher matcher = pattern.matcher(name);
        if(matcher.find() && matcher.group().equals(name)){
            return true;
        }else{
            return false;
        }
    }

    public boolean validatePhone(String phone){
        Pattern pattern = Pattern.compile("[0-9+]*");
        Matcher matcher = pattern.matcher(phone);
        if(matcher.find() && matcher.group().equals(phone)){
            return true;
        }else{
            return false;
        }
    }

    public ObservableList<ClientViewModel> getAllClients() {
        List<Client> clients = clientRepository.getAll();
        System.out.println(clients.toString());

        return FXCollections.observableList(
                clients
                        .stream()
                        .map(c -> new ClientViewModel(
                                c.getId(),
                                c.getFirst_name()+" "+c.getLast_name(),
                                c.getPhone(),
                                c.getAddress(),
                                c.getEgn(),
                                c.getRegister_date().toString(),
                                c.getCity().getName()
                        )).collect(Collectors.toList()));
    }
    public int getTotalClients(){
        Long count = clientRepository.getTotalClients();
        return count.intValue();
    }

    public ObservableList<ClientViewModel> searchClients(String name, Date dateFrom, Date dateTo){
        List<Client> clients = clientRepository.searchClients(name,dateFrom,dateTo);

        return FXCollections.observableList(
                clients
                        .stream()
                        .map(c -> new ClientViewModel(
                                c.getId(),
                                c.getFirst_name()+" "+c.getLast_name(),
                                c.getPhone(),
                                c.getAddress(),
                                c.getEgn(),
                                c.getRegister_date().toString(),
                                c.getCity().getName()
                        )).collect(Collectors.toList()));
    }
}
