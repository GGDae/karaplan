import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CookieService } from 'ngx-cookie-service';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AlertComponent } from './alert/alert.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SongsComponent } from './songs/songs.component';
import { SongListComponent } from './song-list/song-list.component';
import { SongSelectionsComponent } from './song-selections/song-selections.component';
import { SongDetailComponent } from './song-detail/song-detail.component';
import { SongActionsComponent } from './song-actions/song-actions.component';
import { PlaylistsComponent } from './playlists/playlists.component';
import { PlaylistDetailComponent } from './playlist-detail/playlist-detail.component';
import { PlaylistEditModalComponent } from './playlist-edit-modal/playlist-edit-modal.component';
import { PlaylistLeaveModalComponent } from './playlist-leave-modal/playlist-leave-modal.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { AboutComponent } from './about/about.component';
import { DurationPipe } from './pipes/duration.pipe';
import { HttpErrorInterceptor } from './http-error.interceptor';
import { PlyrModule } from 'ngx-plyr';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AlertComponent,
    FooterComponent,
    HomeComponent,
    LoginComponent,
    SongsComponent,
    SongListComponent,
    SongSelectionsComponent,
    SongDetailComponent,
    SongActionsComponent,
    PlaylistsComponent,
    PlaylistDetailComponent,
    PlaylistEditModalComponent,
    PlaylistLeaveModalComponent,
    UserProfileComponent,
    AboutComponent,
    DurationPipe
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    AppRoutingModule,
    PlyrModule
  ],
  providers: [
    CookieService,
    { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    PlaylistEditModalComponent,
    PlaylistLeaveModalComponent
  ]
})
export class AppModule { }
