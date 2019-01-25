package Views.ViewRapoarte;

import Models.Nota;
import Models.Student;
import Models.Tema;
import Services.CatalogService;
import Utils.ObserverDP.ListEvent;
import Utils.ObserverDP.Observer;
import Utils.Pair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ViewRapoarteController implements Observer {

    private CatalogService service;
    private ObservableList<Pair<Integer,Integer>> modelNrMedii = FXCollections.observableArrayList();
    private ObservableList<Pair<String,Integer>> modelNepredate = FXCollections.observableArrayList();

    private ObservableList<Student> modelStudent = FXCollections.observableArrayList();
    private ObservableList<Tema> modelTema = FXCollections.observableArrayList();
    private ObservableList<Nota> modelNota = FXCollections.observableArrayList();


    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    @FXML
    private Label labelCeaMaiGrea;
    @FXML
    private Label labelCeaMaiGreaNr;
    @FXML
    private PieChart pieChart;
    @FXML
    private TableView tableNepredate;
    @FXML
    private TableColumn columnStudent;
    @FXML
    private TableColumn columnTema;




    @FXML
    public void initialize() {
        //labelCeaMaiGrea.setText(service.ceaMaiGreaTema().getFirst().toString());
        pieChart.setTitle("Distributia Mediilor");
        columnStudent.setCellValueFactory(new PropertyValueFactory<Pair<String,Integer>,String>("First"));
        columnTema.setCellValueFactory(new PropertyValueFactory<Pair<String,Integer>,String>("Second"));


    }

    public void setLabels() {
        Pair<Tema,Integer> aux = service.ceaMaiGreaTema();
        Tema tm = aux.getFirst();
        labelCeaMaiGrea.setText(String.valueOf(tm.getId()));
        labelCeaMaiGreaNr.setText(String.valueOf(aux.getSecond()));

    }

    public void setService(CatalogService service) {
        this.service = service;

        modelStudent.setAll(service.getServiceStudent().getAllStudents());
        modelNota.setAll(service.getServiceNota().getAllNote());
        modelTema.setAll(service.getServiceTema().getAllTeme());
        modelNepredate.setAll(getNepredate());
        tableNepredate.setItems(modelNepredate);
        setLabels();
        modelNrMedii.setAll(getNrMedii());
        populatePieChart();

    }

    public void populatePieChart() {
        pieChartData.clear();
        modelNrMedii.forEach(x -> {
            pieChartData.add(new PieChart.Data(x.getFirst().toString(), x.getSecond()));
        });
        pieChart.setData(pieChartData);
    }
    @Override
    public void notifyEvent(ListEvent e) {
        modelStudent.setAll(service.getServiceStudent().getAllStudents());
        modelNota.setAll(service.getServiceNota().getAllNote());
        modelTema.setAll(service.getServiceTema().getAllTeme());
        modelNepredate.setAll(getNepredate());
        modelNrMedii.setAll(getNrMedii());
        populatePieChart();
    }

    public List<Pair<String,Integer>> getNepredate() {
        List<Pair<String,Integer>> rez = new ArrayList<>();
        modelStudent.forEach(st->{
            modelTema.forEach(tm->{
                if(checkNota(st,tm)==false) {
                    Pair<String,Integer> newpair = new Pair<>(st.getId(),tm.getId());
                    rez.add(newpair);
                }
            });
        });
        return rez;
    }
    public boolean checkNota(Student st, Tema tm) {
        String idNota = st.getId() + String.valueOf(tm.getId());
        if(service.getServiceNota().findNota(idNota).isPresent()) {
            if(modelNota.contains(service.getServiceNota().findNota(idNota).get())) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public List<Pair<Integer,Integer>> getNrMedii() {
        List<Integer> medii = new ArrayList<>();

        medii.add(1);
        medii.add(2);
        medii.add(3);
        medii.add(4);
        medii.add(5);
        medii.add(6);
        medii.add(7);
        medii.add(8);
        medii.add(9);
        medii.add(10);
        List<Pair<Integer,Integer>> nrMedii = new ArrayList<>();
        medii.forEach(x->{
            final int[] cate = {0};
            modelStudent.forEach(st->{
                if (Math.round(getMedie(st))==x) {
                    cate[0]++;
                }
            });
            nrMedii.add(new Pair<Integer,Integer>(x,cate[0]));

        });
        return nrMedii;
    }

    public double getMedie(Student st) {
        final double[] medie = {0};
        modelNota.forEach(x->{
            if (x.getIdStudent().compareTo(st.getId())==0) {
                medie[0] = medie[0] + x.getValoare();
            }
        });
        return medie[0]/(modelTema.size());
    }


}

