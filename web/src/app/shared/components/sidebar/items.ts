import { MenuItem } from './model';


export const SIDEBAR_ITEMS: MenuItem[] = [
  // Inicio
  {
    text: 'pages.home',
    path: 'home',
    icon: 'home',
  },
  // Propuestas
  {
    text: 'pages.approach.title', 
    icon: 'work',
    expanded: false,
    children: [{
      text: 'pages.approach.add', path:'approachManagementCreate'
    },
    {
      text: 'pages.approach.list', path:'approachManagementList'
    }
    ]
  },
  // Campañas
  {
    text: 'pages.campaign.title',
    icon: 'campaign',
    expanded: false,
    children: [{
      text: 'pages.campaign.add', path:'campaignManagementCreate'
    },
    {
      text: 'pages.campaign.list', path:'campaignManagementList'
    },
    {
      text: 'pages.campaign.protocol', path:'protocolManagementCreate'
    }
    ]
  },
  //infracciones
  {
    text: 'pages.infraction.title',
    icon: 'report_problem',
    expanded: false,
    children: [
    {
      text: 'pages.infraction.list', path:'infractionManagementList'
    }
    ]
  },
  // Perfiles
  {
    text: 'Gestion de Perfiles',
    icon: 'library_books',
    expanded:false,
    children: [
      {
        text: 'Alta de Perfil',
        expanded:false,
        path:'profileManagementCreate'
      },{
        text: 'Lista de Perfiles',
        expanded:false,
        path:'profileManagementList'
      }
    ]
  },
  // Entidades
  {
    text: 'Gestion de Entidades',
    icon: 'library_books',
    expanded:false,
    children: [
      {
        text: 'Alta de Entidades',
        expanded:false,
        path:'entityManagementCreate'
      },{
        text: 'Lista de Entidades',
        expanded:false,
        path:'entityManagementList'
      }
    ]
  },
  // Usuarios
  {
    text: 'pages.user.title',
    icon: 'person',
    expanded: false,
    children: [{
      text: 'pages.user.add', path:'userManagementCreate' },
    { text: 'pages.user.list', path:'userManagementList' }
    ]
  }

];
