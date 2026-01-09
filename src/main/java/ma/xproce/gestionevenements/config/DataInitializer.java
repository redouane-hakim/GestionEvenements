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
                    .nom("Admin")
                    .prenom("Responsable")
                    .email("admin@demo.com")
                    .telephone("0600000000")
                    .motDePasse(passwordEncoder.encode("admin123"))
                    .role(Role.responsable)
                    .signatureImageUrl("https://example.com/sign-admin.png")
                    .build();

            Utilisateur org = Utilisateur.builder()
                    .nom("Organisateur")
                    .prenom("Demo")
                    .email("org@demo.com")
                    .telephone("0611111111")
                    .motDePasse(passwordEncoder.encode("org123"))
                    .role(Role.organisateur)
                    .signatureImageUrl("https://example.com/sign-org.png")
                    .build();

            utilisateurRepository.save(admin);
            utilisateurRepository.save(org);

            // Une demande acceptée + événement
            Demande d1 = Demande.builder()
                    .nomOrganisateur(org.getNom())
                    .prenomOrganisateur(org.getPrenom())
                    .emailOrganisateur(org.getEmail())
                    .telephoneOrganisateur(org.getTelephone())
                    .nomOrganisation("XPROCE Club")
                    .typeEvenement("Conférence")
                    .intitule("Tech Day 2026")
                    .description("Journée de conférences et ateliers autour de Spring Boot, Cloud et DevOps.")
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
                    .lieu("Casablanca")
                    .dateDebut(LocalDateTime.now().plusDays(10))
                    .dateFin(LocalDateTime.now().plusDays(10).plusHours(6))
                    .categorie("Conférence")
                    .afficheUrl("https://images.unsplash.com/photo-1521737604893-d14cc237f11d")
                    .organisateur(org)
                    .demande(d1)
                    .build();
            evenementRepository.save(e1);

            // Événements publics pour le carousel
            for (int i = 2; i <= 6; i++) {
                Evenement e = Evenement.builder()
                        .titre("Événement Démo " + i)
                        .description("Description de démonstration pour l'événement " + i + ".")
                        .lieu("Rabat")
                        .dateDebut(LocalDateTime.now().plusDays(5 + i))
                        .dateFin(LocalDateTime.now().plusDays(5 + i).plusHours(3))
                        .categorie("Meetup")
                        .afficheUrl("https://images.unsplash.com/photo-1515165562835-c4c6e925b0d6")
                        .organisateur(org)
                        .build();
                evenementRepository.save(e);
            }
        };
    }
}
