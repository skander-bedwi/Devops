package tn.esprit.foyer.Services.Imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.foyer.Entities.Bloc;
import tn.esprit.foyer.Entities.Chambre;
import tn.esprit.foyer.Entities.Foyer;
import tn.esprit.foyer.Repositories.*;
import tn.esprit.foyer.Services.IBlocServices;

import java.util.List;

@Service

@Slf4j
@RequiredArgsConstructor
public class IBlocServicesImp implements IBlocServices {

    private final IBlocRepository blocRepository;
    private final IChambreRepository chambreRepository;
    private final IFoyerRepository foyerRepository;


    @Override
    public Bloc addBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public Bloc updateBloc(Bloc bloc) {
        return blocRepository.save(bloc);
    }

    @Override
    public List<Bloc> getAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc getBlocById(Long idBloc) {
        return blocRepository.findById(idBloc).orElse(null);
    }

    @Override
    public void deleteBloc(Long idBloc) {
        blocRepository.deleteById(idBloc);
    }


    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, Long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc).orElseThrow(()->new IllegalArgumentException("Ce bloc n'existe pas"));
        for(Long num:numChambre){
            Chambre chambre = chambreRepository.findChambreByNumeroChambre(num);
            chambre.setBloc(bloc);
            chambreRepository.save(chambre);
        }
        return bloc;
    }

    @Override
    public Bloc affecterBlocAFoyer(Long idBloc, Long idFoyer) {
        Bloc bloc = blocRepository.findById(idBloc).orElseThrow(()->new IllegalArgumentException("Ce bloc n'existe pas"));
        Foyer foyer = foyerRepository.findById(idFoyer).orElseThrow(()->new IllegalArgumentException("Ce foyer n'existe pas"));
        bloc.setFoyer(foyer);
        blocRepository.save(bloc);
        return bloc;
    }

    @Override
    @Scheduled(fixedRate = 10000)
    public void testschedulure() {
    log.info("salem skon");
//       List<Bloc> blocs = blocRepository.findAll();
//
//

//       for(Bloc bloc: blocs){
//           log.info(String.valueOf((chambreRepository.findChambreByBloc(bloc).size())));
//       }




    }
}
