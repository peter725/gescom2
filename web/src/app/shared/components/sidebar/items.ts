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
    requireAccess: 'approach',
    expanded: false,
    children: [{
      text: 'pages.approach.add', path:'approachManagementCreate', requireAccess: 'approach', requireScope: 'ww'
    },
    {
      text: 'pages.approach.list', path:'approachManagementList', requireAccess: 'approach', requireScope: 'rr'
    },
    ]
  },
  // Campañas
  {
    text: 'pages.campaign.title',
    icon: 'campaign',
    requireAccess: 'campaign',
    expanded: false,
    children: [{
      text: 'pages.campaign.add', path:'campaignManagementCreate', requireAccess: 'campaign', requireScope: 'ww'
    },
    {
      text: 'pages.campaign.list', path:'campaignManagementList', requireAccess: 'campaign', requireScope: 'rr'
    },
    ]
  },
  // Protocolos
  // {
  //   text: 'pages.protocol.title',
  //   icon: 'assignment',
  //   expanded: false,
  //   children: [{
  //     text: 'pages.protocol.add', path:'protocolManagementCreate'
  //   },
  //   {
  //     text: 'pages.protocol.list', path:'protocolManagementList'
  //   }
  //   ]
  // },
  //infracciones
  // {
  //   text: 'pages.infringement.title',
  //   icon: 'report_problem',
  //   expanded: false,
  //   children: [
  //   {
  //     text: 'pages.infringement.list', path:'infringementManagementList'
  //   }
  //   ]
  // },
  // Perfiles
  // {
  //   text: 'Gestion de Perfiles',
  //   icon: 'library_books',
  //   requireAccess: 'profile',
  //   expanded:false,
  //   children: [
  //     {
  //       text: 'Alta de Perfil',
  //       expanded:false,
  //       requireAccess: 'profile',
  //       requireScope: 'ww',
  //       path:'profileManagementCreate'
  //     },{
  //       text: 'Lista de Perfiles',
  //       expanded:false,
  //       requireAccess: 'profile',
  //       requireScope: 'rr',
  //       path:'profileManagementList'
  //     }
  //   ]
  // },
  // Roles
  {
    text: 'Gestion de Roles',
    icon: 'library_books',
    requireAccess: 'role',
    expanded:false,
    children: [
      {
        text: 'Alta de Roles',
        expanded:false,
        requireAccess: 'role',
        requireScope: 'ww',
        path:'roleManagementCreate'
      },{
        text: 'Lista de Roles',
        expanded:false,
        requireAccess: 'role',
        requireScope: 'rr',
        path:'roleManagementList'
      }
    ]
  },
  // Entidades
  // {
  //   text: 'Gestion de Entidades',
  //   icon: 'library_books',
  //   expanded:false,
  //   requireAccess: 'entity',
  //   children: [
  //     {
  //       text: 'Alta de Entidades',
  //       expanded:false,
  //       requireAccess: 'entity',
  //       requireScope: 'ww',
  //       path:'entityManagementCreate'
  //     },{
  //       text: 'Lista de Entidades',
  //       expanded:false,
  //       requireAccess: 'entity',
  //       requireScope: 'rr',
  //       path:'entityManagementList'
  //     }
  //   ]
  // },
  // Usuarios
  {
    text: 'pages.user.title',
    icon: 'person',
    expanded: false,
    requireAccess: 'user',
    children: [{
      text: 'pages.user.add', path:'userManagementCreate', requireAccess: 'user', requireScope: 'ww' },
    { text: 'pages.user.list', path:'userManagementList', requireAccess: 'user', requireScope: 'rr' }
    ]
  }

];
