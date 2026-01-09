package ma.xproce.gestionevenements.config;

import lombok.RequiredArgsConstructor;
import ma.xproce.gestionevenements.dao.entities.*;
import ma.xproce.gestionevenements.dao.repositories.DemandeRepository;
import ma.xproce.gestionevenements.dao.repositories.EvenementRepository;
import ma.xproce.gestionevenements.dao.repositories.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    @Bean
    CommandLineRunner init(UtilisateurRepository utilisateurRepository,
                           DemandeRepository demandeRepository,
                           EvenementRepository evenementRepository,
                           PasswordEncoder passwordEncoder) {
        return args -> {
            if (utilisateurRepository.count() > 0) return;

            Utilisateur admin = Utilisateur.builder()
                    .nom("Hakim")
                    .prenom("Redouane")
                    .email("admin@demo.com")
                    .telephone("0612345678")
                    .motDePasse(passwordEncoder.encode("admin123"))
                    .role(Role.responsable)
                    .signatureImageUrl("https://via.placeholder.com/150x50/4A90E2/FFFFFF?text=R.Hakim")
                    .build();

            Utilisateur org = Utilisateur.builder()
                    .nom("Sekkat")
                    .prenom("Yassin")
                    .email("org@demo.com")
                    .telephone("0698765432")
                    .motDePasse(passwordEncoder.encode("org123"))
                    .role(Role.organisateur)
                    .signatureImageUrl("https://via.placeholder.com/150x50/E94B3C/FFFFFF?text=Y.Sekkat")
                    .build();

            utilisateurRepository.save(admin);
            utilisateurRepository.save(org);

            // Une demande acceptée avec événement
            Demande d1 = Demande.builder()
                    .nomOrganisateur(org.getNom())
                    .prenomOrganisateur(org.getPrenom())
                    .emailOrganisateur(org.getEmail())
                    .telephoneOrganisateur(org.getTelephone())
                    .nomOrganisation("Innovation Hub Maroc")
                    .typeEvenement("Conférence Technologique")
                    .intitule("DevFest Casablanca 2026")
                    .description("Grande conférence annuelle dédiée aux développeurs avec des ateliers sur le Cloud, l'Intelligence Artificielle et les technologies Web modernes.")
                    .signatureOrganisateurImageUrl(org.getSignatureImageUrl())
                    .dateDemande(LocalDateTime.now().minusDays(5))
                    .status(Status.ACCEPTEE)
                    .validateur(admin)
                    .dateValidation(LocalDateTime.now().minusDays(4))
                    .build();
            demandeRepository.save(d1);

            Evenement e1 = Evenement.builder()
                    .titre(d1.getIntitule())
                    .description(d1.getDescription())
                    .lieu("Technopark Casablanca")
                    .dateDebut(LocalDateTime.now().plusDays(10))
                    .dateFin(LocalDateTime.now().plusDays(10).plusHours(6))
                    .categorie("Conférence Technologique")
                    .afficheUrl("https://images.unsplash.com/photo-1540575467063-178a50c2df87?w=800")
                    .organisateur(org)
                    .demande(d1)
                    .build();
            evenementRepository.save(e1);

            // Événement 2
            Evenement e2 = Evenement.builder()
                    .titre("Workshop Intelligence Artificielle")
                    .description("Atelier pratique sur le Machine Learning et les réseaux de neurones avec Python et TensorFlow pour débutants et intermédiaires.")
                    .lieu("Station F Rabat")
                    .dateDebut(LocalDateTime.now().plusDays(7))
                    .dateFin(LocalDateTime.now().plusDays(7).plusHours(4))
                    .categorie("Atelier")
                    .afficheUrl("https://images.unsplash.com/photo-1505373877841-8d25f7d46678?w=800")
                    .organisateur(org)
                    .build();
            evenementRepository.save(e2);

            // Événement 3
            Evenement e3 = Evenement.builder()
                    .titre("Startup Weekend Marrakech")
                    .description("Weekend intensif pour créer votre startup en 54 heures avec mentorat d'experts et pitch final devant un jury d'investisseurs.")
                    .lieu("Marrakech Tech City")
                    .dateDebut(LocalDateTime.now().plusDays(12))
                    .dateFin(LocalDateTime.now().plusDays(14))
                    .categorie("Hackathon")
                    .afficheUrl("https://images.unsplash.com/photo-1475721027785-f74eccf877e2?w=800")
                    .organisateur(org)
                    .build();
            evenementRepository.save(e3);

            // Événement 4
            Evenement e4 = Evenement.builder()
                    .titre("Cybersecurity Summit")
                    .description("Sommet annuel sur la cybersécurité réunissant experts internationaux et professionnels marocains autour des enjeux de sécurité informatique.")
                    .lieu("Mohammed VI Polytechnic University")
                    .dateDebut(LocalDateTime.now().plusDays(15))
                    .dateFin(LocalDateTime.now().plusDays(15).plusHours(5))
                    .categorie("Conférence")
                    .afficheUrl("https://images.unsplash.com/photo-1591115765373-5207764f72e7?w=800")
                    .organisateur(org)
                    .build();
            evenementRepository.save(e4);

            // Événement 5
            Evenement e5 = Evenement.builder()
                    .titre("Design Thinking Bootcamp")
                    .description("Formation intensive de trois jours sur les méthodologies Design Thinking pour innover et résoudre des problèmes complexes en équipe.")
                    .lieu("EMSI Casablanca")
                    .dateDebut(LocalDateTime.now().plusDays(18))
                    .dateFin(LocalDateTime.now().plusDays(20))
                    .categorie("Formation")
                    .afficheUrl("https://images.unsplash.com/photo-1540575467063-178a50c2df87?w=800")
                    .organisateur(org)
                    .build();
            evenementRepository.save(e5);

            // Événement 6
            Evenement e6 = Evenement.builder()
                    .titre("Forum Employabilité Tech 2026")
                    .description("Grande rencontre annuelle entre étudiants en informatique et entreprises technologiques leaders pour découvrir les opportunités de carrière.")
                    .lieu("Université Hassan II Casablanca")
                    .dateDebut(LocalDateTime.now().plusDays(22))
                    .dateFin(LocalDateTime.now().plusDays(22).plusHours(6))
                    .categorie("Forum")
                    .afficheUrl("https://images.unsplash.com/photo-1505373877841-8d25f7d46678?w=800")
                    .organisateur(org)
                    .build();
            evenementRepository.save(e6);
        };
    }
}