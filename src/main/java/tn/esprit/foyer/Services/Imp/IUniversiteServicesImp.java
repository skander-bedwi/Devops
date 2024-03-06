package tn.esprit.foyer.Services.Imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.foyer.Entities.Foyer;
import tn.esprit.foyer.Entities.Universite;
import tn.esprit.foyer.Repositories.*;
import tn.esprit.foyer.Services.IUniversiteServices;


import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IUniversiteServicesImp implements IUniversiteServices {

    private final IUniversiteRepository universiteRepository;
    private final IFoyerRepository foyerRepository;


    @Override
    public Universite addUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public Universite updateUniversite(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public List<Universite> getAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite getUniversiteById(Long idUniversite) {
        return universiteRepository.findById(idUniversite).orElseThrow(() -> new IllegalArgumentException("Cette universite n'existe pas"));
    }

    @Override

    public Universite getUniversiteByNom(String nomUniversite) {

        return universiteRepository.findByNomUniversiteContainingIgnoreCase(nomUniversite);

    }

    @Override
    public void deleteUniversite(long idUniversite) {
        universiteRepository.deleteById(idUniversite);
    }


    @Override
    public Universite affecterFoyerAUniversite(Long idFoyer, String nomUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElseThrow(() -> new IllegalArgumentException("Ce foyer n'existe pas"));

        Universite universite = universiteRepository.findByNomUniversiteContainingIgnoreCase(nomUniversite);

        universite.setFoyer(foyer);
        return universiteRepository.save(universite);
    }

    @Override
    public Universite desaffecterFoyerAUniversite(Long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElseThrow(() -> new IllegalArgumentException("Cette universite n'existe pas"));

        universite.setFoyer(null);
        return universiteRepository.save(universite);
    }




}